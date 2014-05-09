package com.jcdesimp.canvaskits.interfaceview;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class ViewManager implements Listener {
    HashMap<String, KitView> activeViews;

    public ViewManager() {
        this.activeViews = new HashMap<String, KitView>();
    }

    public void activateView(Player p){
        KitView newView = new KitView(p);
        newView.showUI();
        activeViews.put(p.getName(),newView) ;
    }

    public void deactivateView(Player p){
        if(activeViews.containsKey(p.getName())){
            activeViews.get(p.getName()).closeView();
            activeViews.remove(p.getName());
        }
    }

    public void deactivateView(String pName){
        if(activeViews.containsKey(pName)){
            activeViews.get(pName).closeView();
            activeViews.remove(pName);
        }
    }

    public void deactivateAll(){
        for(String s : activeViews.keySet()) {
            deactivateView(s);
        }
    }

}
