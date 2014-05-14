package com.jcdesimp.canvaskits.kitstruct;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Date;

/**
 * File created by jcdesimp on 5/2/14.
 */
public interface Kit {


    /**
     * gets display name of kit (for UI)
     * @return display name
     */
    public String getDisplayName();

    /**
     * Gets description lines (Item lore)
     * @return description
     */
    public ArrayList<String> getDescription();

    /**
     * get the item stack that visually represents
     * this kit
     * @return the item stack
     */
    public ItemStack getDisplayItem();




}
