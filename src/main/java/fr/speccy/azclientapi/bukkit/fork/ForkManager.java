package fr.speccy.azclientapi.bukkit.fork;

import org.bukkit.plugin.Plugin;

public class ForkManager {

    public static void loadPactifyPlayer(Plugin pl){
        pl.getServer().getPluginManager().registerEvents(new PactifyPlayer(), pl);
    }
}
