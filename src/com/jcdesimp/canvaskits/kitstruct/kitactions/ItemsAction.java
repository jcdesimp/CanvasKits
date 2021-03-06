package com.jcdesimp.canvaskits.kitstruct.kitactions;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * File created by jcdesimp on 5/14/14.
 */
public class ItemsAction implements KitAction {

    private ItemStack item;

    public ItemsAction(ItemStack item) {
        this.item = item;
    }

    /**
     * Give item to a player
     * @param player to give the items to.
     */
    @Override
    public void call(Player player) {
        HashMap<Integer,ItemStack>  left = player.getInventory().addItem(item);
        if(!left.isEmpty()) {
            Location loc = player.getLocation();
            loc.setY(loc.getBlockY()+2);
            player.getWorld().dropItem(loc, item);
        }



    }
}
