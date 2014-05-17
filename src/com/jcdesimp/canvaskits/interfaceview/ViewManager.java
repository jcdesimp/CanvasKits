package com.jcdesimp.canvaskits.interfaceview;

import com.jcdesimp.canvaskits.CanvasKits;
import com.jcdesimp.canvaskits.kitstruct.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class ViewManager implements Listener {
    private HashMap<String, KitView> activeViews;
    private CanvasKits plugin;

    public ViewManager(CanvasKits plugin) {

        this.activeViews = new HashMap<String, KitView>();
        this.plugin = plugin;
    }


    public void addView(Player p) {
        activeViews.put(p.getName(), new KitView(p, this, plugin.getKitMan().getHeadKits()));
    }

    public void clearView(String p) {
        activeViews.remove(p);
    }

    public void clearAll(){

    }

    public void changeView(Player p, KitView v) {
         activeViews.put(p.getName(), v);
    }

    @EventHandler
    public void clickInvSlot( InventoryClickEvent event ) {
        if (!event.getInventory().getTitle().equals("CANVAS Kits")){
            return;
        }
        event.setCancelled(true);
        activeViews.get(event.getWhoClicked().getName()).slotClicked(event.getRawSlot());
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent event) {
        if(!event.getInventory().getTitle().equals("CANVAS Kits")) {
            return;
        }
        clearView(event.getPlayer().getName());

    }





}
