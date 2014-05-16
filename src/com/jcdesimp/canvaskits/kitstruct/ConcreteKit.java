package com.jcdesimp.canvaskits.kitstruct;

import com.jcdesimp.canvaskits.kitstruct.kitactions.KitAction;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class ConcreteKit implements Kit {
    String displayName;
    ArrayList<String> description;
    ItemStack displayItem;
    ArrayList<KitAction> kitActions;
    String uniqueName;
    int cooldown = 0;

    public ConcreteKit(String displayName, List<String> desc, ItemStack displayItem, String uName) {
        this.displayName = displayName;
        this.uniqueName = uName;
        this.description = new ArrayList<String>();
        for(String s : desc) {
            description.add(ChatColor.RESET+s);
        }
        this.displayItem = displayItem;
        this.kitActions = new ArrayList<KitAction>();
    }


    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    /**
     * check if player
     * on cooldown
     * @param p
     * @return
     */
    public boolean onCooldown(Player p) {
        return true;

    }
    /**
     * gets cooldown remainder
     * @param p
     * @return
     */
    public long getPCooldown(Player p) {

        return cooldown;
    }

    public void addKitAction(KitAction ka) {
        if(ka != null) {
            kitActions.add(ka);
        }

    }

    public void activate(Player p) {
        for(KitAction k: kitActions){
            k.call(p);

        }
    }


    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public ArrayList<String> getDescription() {
        return description;
    }

    @Override
    public ItemStack getDisplayItem() {
        return displayItem;
    }

    @Override
    public String getUniqueName() {
        return uniqueName;
    }
}
