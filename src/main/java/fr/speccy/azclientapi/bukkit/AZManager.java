package fr.speccy.azclientapi.bukkit;

import java.io.IOException;

import fr.speccy.azclientapi.bukkit.packets.PacketOutBuffer;
import fr.speccy.azclientapi.bukkit.utils.BukkitUtil;
import org.bukkit.event.HandlerList;
import java.util.logging.Level;

import pactify.client.api.mcprotocol.NotchianPacketBuffer;
import pactify.client.api.mcprotocol.util.NotchianPacketUtil;
import pactify.client.api.plsp.PLSPProtocol;
import pactify.client.api.plsp.PLSPPacketHandler;
import pactify.client.api.plsp.PLSPPacket;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.event.player.PlayerLoginEvent;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;
import org.bukkit.plugin.Plugin;
import java.io.Closeable;
import org.bukkit.event.Listener;

public class AZManager implements Listener, Closeable {
    private final Plugin plugin;
    private final int serverVersion;
    private static final String PLSP_CHANNEL = "PLSP";
    private final Map<UUID, AZPlayer> players;

    public AZManager(final Plugin plugin) {
        this.players = new HashMap<UUID, AZPlayer>();
        this.plugin = plugin;
        this.serverVersion = BukkitUtil.findServerVersion();
        plugin.getServer().getPluginManager().registerEvents((Listener)this, plugin);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "PLSP");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLogin(final PlayerLoginEvent event) {
        event.getPlayer().setMetadata("AZClientPlugin:hostname", (MetadataValue)new FixedMetadataValue(this.plugin, (Object)event.getHostname()));
        final AZPlayer AZPlayer;
        this.players.put(event.getPlayer().getUniqueId(), AZPlayer = new AZPlayer(this, event.getPlayer()));
        AZPlayer.init();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLoginMonitor(final PlayerLoginEvent event) {
        if (event.getResult() != PlayerLoginEvent.Result.ALLOWED) {
            this.playerQuit(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final AZPlayer AZPlayer = this.getPlayer(event.getPlayer());
        AZPlayer.join();
    }

    public AZPlayer getPlayer(final Player player) {
        return this.players.get(player.getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        this.playerQuit(event.getPlayer());
    }

    private void playerQuit(final Player player) {
        final AZPlayer AZPlayer = this.players.remove(player.getUniqueId());
        if (AZPlayer != null) {
            AZPlayer.free(true);
        }
    }

    public void sendPLSPMessage(final Player player, final PLSPPacket<PLSPPacketHandler.ClientHandler> message) {
        try {
            final PacketOutBuffer buf = new PacketOutBuffer();
            final PLSPProtocol.PacketData<?> packetData = (PLSPProtocol.PacketData<?>)PLSPProtocol.getClientPacketByClass((Class)message.getClass());
            NotchianPacketUtil.writeString((NotchianPacketBuffer)buf, packetData.getId(), 32767);
            message.write((NotchianPacketBuffer)buf);
            player.sendPluginMessage(this.plugin, "PLSP", buf.toBytes());
        }
        catch (Exception e) {
            this.plugin.getLogger().log(Level.WARNING, "Exception sending PLSP message to " + ((player != null) ? player.getName() : "null") + ":", e);
        }
    }

    public void close() throws IOException {
        HandlerList.unregisterAll((Listener)this);
        this.plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(this.plugin, "PLSP");
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public int getServerVersion() {
        return this.serverVersion;
    }

    public static Integer getColor(final String hexColor) {
        String hexValue = "0xFF" + hexColor;
        long longValue = Long.parseLong(hexValue.substring(2), 16);
        return (int) longValue;
    }
}
