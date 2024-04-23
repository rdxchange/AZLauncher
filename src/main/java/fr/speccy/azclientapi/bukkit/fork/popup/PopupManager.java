package fr.speccy.azclientapi.bukkit.fork.popup;

import fr.speccy.azclientapi.bukkit.AZClientPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

import static fr.speccy.azclientapi.bukkit.AZClientPlugin.*;

public class PopupManager {
    public static HashMap<Integer, Player> playerPopup = new HashMap<>();
    public static HashMap<Integer, String> popup = new HashMap<>();
    public static HashMap<Integer, String[]> popupMessage = new HashMap<>();
    public static HashMap<Integer, Material> popupItem = new HashMap<>();

    public static void create(Integer id, String name, Material item, String[] message){
        popup.put(id, name);
        popupMessage.put(id, message);
        main.getLogger().info("A pop-up was created under the id: " + id);
    }

    public static void delete(Integer id){
        popup.remove(id);
        popupMessage.remove(id);
        main.getLogger().info("A pop-up was destroyed under the id: " + id);
    }

    public static void open(Integer id, Player player){
        if(popup.containsKey(id)){
            playerPopup.put(id, player);
        }
    }

    public static void remove(Integer id, Player player){
        if(playerPopup.containsKey(player)){
            playerPopup.remove(player);
        }
    }
}
