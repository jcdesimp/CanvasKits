package com.jcdesimp.canvaskits;

import org.bukkit.entity.Player;

import java.util.Date;

/**
 * File created by jcdesimp on 5/2/14.
 */
public interface Kit {

    public void giveKit(Player p);


    /**
     * Checks to see if player is on cooldown
     * @param p to check
     */
    public void onCooldown(Player p);

    /**
     * gets cooldown remainder
     * @param p
     * @return
     */
    public Date getCooldown(Player p);

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
