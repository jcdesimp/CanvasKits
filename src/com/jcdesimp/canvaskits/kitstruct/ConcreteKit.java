package com.jcdesimp.canvaskits.kitstruct;

import org.bukkit.entity.Player;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class ConcreteKit implements Kit {



    /**
     * Give the kit to the player
     * @param p
     */
    public void giveKit(Player p) {

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
    public long getCooldown(Player p) {
        return 0;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String[] getDescription() {
        return new String[0];
    }
}
