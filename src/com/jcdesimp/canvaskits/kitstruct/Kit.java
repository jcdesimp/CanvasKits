package com.jcdesimp.canvaskits.kitstruct;

import org.bukkit.entity.Player;

import java.util.Date;

/**
 * File created by jcdesimp on 5/2/14.
 */
public interface Kit {





    /**
     * gets display name of kit (for UI)
     * @return
     */
    public String getDisplayName();

    /**
     * Gets description lines (Item lore)
     * @return
     */
    public String[] getDescription();




}
