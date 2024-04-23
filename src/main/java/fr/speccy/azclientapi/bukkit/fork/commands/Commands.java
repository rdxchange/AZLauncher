package fr.speccy.azclientapi.bukkit.fork.commands;

import fr.speccy.azclientapi.bukkit.fork.popup.PopupManager;
import fr.speccy.azclientapi.bukkit.handlers.PLSPConfFlag;
import fr.speccy.azclientapi.bukkit.handlers.PLSPWorldEnv;
import fr.speccy.azclientapi.bukkit.packets.PacketConfFlag;
import fr.speccy.azclientapi.bukkit.packets.PacketEntityMeta;
import fr.speccy.azclientapi.bukkit.packets.PacketWorldEnv;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command c, String l, String[] args) {
        if(l.equalsIgnoreCase("testpop")){
            PopupManager.create(1, "§6Test", Material.AIR, new String[]{"fezf"});
        }
        if(l.equalsIgnoreCase("scale")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("Cette commande est réservée aux joueurs !");
                return true;
            }

            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage("§cUsage: /scale <taille>");
                return true;
            }

            float scale;

            try {
                scale = Float.parseFloat(args[0]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cErreur: L'arguments rentré doit être un nombre !");
                return true;
            }

            player.sendMessage("§6[Scale] §fTu es désormais à une taille de : §b" + scale);
            PacketEntityMeta.setPlayerScale(player, scale, scale, scale, scale, scale, true);

            return true;
        }

        if(l.equalsIgnoreCase("worldenv")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("Cette commande est réservée aux joueurs !");
                return true;
            }

            Player player = (Player) sender;

            if (args.length < 1) {
                player.sendMessage("§cUsage: /worldenv <environnement/help>");
                return true;
            }

            String env = args[0];
            if(env.equalsIgnoreCase("help")){
                player.sendMessage("§6-WorldEnv§6§m---------------------------------");
                player.sendMessage("§eListe des environnements :");
                player.sendMessage("§6> §fNETHER");
                player.sendMessage("§6> §fEND");
                player.sendMessage("§6> §fNORMAL");
                player.sendMessage("§6§m--------§6§m--------------------------------");
            } else if(env.equalsIgnoreCase("nether") || env.equalsIgnoreCase("NETHER")){
                PacketWorldEnv.setWorldEnv(player, PLSPWorldEnv.NETHER);
                player.sendMessage("§6[WorldEnv] §fTu as modifié ton environnement en §bNETHER");
            } else if(env.equalsIgnoreCase("end") || env.equalsIgnoreCase("END")){
                PacketWorldEnv.setWorldEnv(player, PLSPWorldEnv.THE_END);
                player.sendMessage("§6[WorldEnv] §fTu as modifié ton environnement en §bTHE_END");
            } else if(env.equalsIgnoreCase("normal") || env.equalsIgnoreCase("NORMAL")){
                PacketWorldEnv.setWorldEnv(player, PLSPWorldEnv.NORMAL);
                player.sendMessage("§6[WorldEnv] §fTu as modifié ton environnement en §bNORMAL");
            }
        }

//        if(l.equalsIgnoreCase("plsp")){
//            if (!(sender instanceof Player)) {
//                sender.sendMessage("Cette commande est réservée aux joueurs !");
//                return true;
//            }
//
//            Player player = (Player) sender;
//
//            if (args.length < 2) {
//                player.sendMessage("§cUsage: /plsp <joueur> <param/help> <true|false>");
//                return true;
//            }
//
//            Player target = player.getServer().getPlayer(args[0]);
//            if (target == null) {
//                player.sendMessage("§cLe joueur spécifié n'est pas en ligne !");
//                return true;
//            }
//
//            String param = args[1].toUpperCase();
//            boolean value;
//
//            try {
//                value = Boolean.parseBoolean(args[2]);
//            } catch (NumberFormatException e) {
//                player.sendMessage("§cLa valeur doit être 'true' ou 'false' !");
//                return true;
//            }
//
//            if(param.equalsIgnoreCase("help")){
//                player.sendMessage("§6-Aide§6§m---------------------------------");
//                player.sendMessage("§6> §f" + PLSPConfFlag.ATTACK_COOLDOWN.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.PLAYER_PUSH.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.LARGE_HITBOX.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.SWORD_BLOCKING.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.HIT_AND_BLOCK.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.OLD_ENCHANTEMENTS.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.PVP_HIT_PRIORITY.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.SEE_CHUNKS.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.SIDEBAR_SCORES.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.SMOOTH_EXPERIENCE_BAR.getName());
//                player.sendMessage("§6> §f" + PLSPConfFlag.SORT_TAB_LIST_BY_NAMES.getName());
//                player.sendMessage("§6§m-----§6§m---------------------------------");
//                player.sendMessage("§6§m----§6§m---------------------------------");
//            } else {
//                switch (param) {
//                    case "HIT_AND_BLOCK":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.HIT_AND_BLOCK, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "SWORD_BLOCKING":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.SWORD_BLOCKING, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "ATTACK_COOLDOWN":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.ATTACK_COOLDOWN, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "PLAYER_PUSH":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.PLAYER_PUSH, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "LARGE_HITBOX":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.LARGE_HITBOX, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "OLD_ENCHANTEMENTS":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.OLD_ENCHANTEMENTS, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "PVP_HIT_PRIORITY":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.PVP_HIT_PRIORITY, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "SEE_CHUNKS":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.SEE_CHUNKS, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "SIDEBAR_SCORES":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.SIDEBAR_SCORES, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "SMOOTH_EXPERIENCE_BAR":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.SMOOTH_EXPERIENCE_BAR, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    case "SORT_TAB_LIST_BY_NAMES":
//                        PacketConfFlag.setFlag(player, PLSPConfFlag.SORT_TAB_LIST_BY_NAMES, value);
//                        player.sendMessage("§aParamètre " + param + " défini sur " + value + " pour " + target.getName());
//                        break;
//                    default:
//                        player.sendMessage("§cParamètre non reconnu !");
//                        break;
//                }
//            }
//        }
        return false;
    }
}
