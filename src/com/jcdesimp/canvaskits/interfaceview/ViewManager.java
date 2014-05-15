package com.jcdesimp.canvaskits.interfaceview;

import com.jcdesimp.canvaskits.CanvasKits;
import com.jcdesimp.canvaskits.kitstruct.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

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

    }

    public void clearView(Player p) {

    }

    public void clearAll(){

    }





}
