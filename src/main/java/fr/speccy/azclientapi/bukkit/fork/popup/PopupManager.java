package fr.speccy.azclientapi.bukkit.fork.popup;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class PopupManager {
    public static HashMap<Integer, Player> playerPopup = new HashMap<>();
    public static HashMap<Integer, String> popup = new HashMap<>();
    public static HashMap<Integer, String[]> popupMessage = new HashMap<>();

    // Arrivera prochainement.
    // nathan818 s'y penchera plus tard.

    public static void create(Integer id, String name, ItemStack item, String[] message){
        popup.put(id, name);
        popupMessage.put(id, message);
    }

    public static void delete(Integer id){
        popup.remove(id);
        popupMessage.remove(id);
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
