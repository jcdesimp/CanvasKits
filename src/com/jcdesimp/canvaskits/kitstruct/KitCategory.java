package com.jcdesimp.canvaskits.kitstruct;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class KitCategory implements Kit {


    String displayName;
    String[] description;
    ItemStack displayItem;
    ArrayList<Kit> kits;

    public KitCategory( String displayName, String[] description, ItemStack displayItem) {
        this.displayItem = displayItem;
        this.description = description;
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
    public String[] getDescription() {
        return description;
    }

    @Override
    public ItemStack getDisplayItem() {
        return displayItem;
    }
}
