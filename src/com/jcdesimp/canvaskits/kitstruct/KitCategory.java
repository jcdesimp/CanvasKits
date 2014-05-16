package com.jcdesimp.canvaskits.kitstruct;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class KitCategory implements Kit {


    String displayName;
    ArrayList<String> description;
    ItemStack displayItem;
    ArrayList<Kit> kits;
    String uniqueName;

    public KitCategory( String displayName, List<String> desc, ItemStack displayItem, String uName) {
        this.displayItem = displayItem;
        this.uniqueName=uName;
        this.description = new ArrayList<String>();
        for(String s : desc) {
            description.add(ChatColor.RESET+s);
        }
        this.displayName = displayName;
        this.kits = new ArrayList<Kit>();

    }

    public void addKit(Kit k) {
        kits.add(k);
    }

    public ArrayList<Kit> getKits() {
        return kits;
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
