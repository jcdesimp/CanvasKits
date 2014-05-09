package com.jcdesimp.canvaskits.kitstruct;

import org.bukkit.entity.Player;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class KitCategory implements Kit {


    public void onCooldown(Player p) {

    }

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
