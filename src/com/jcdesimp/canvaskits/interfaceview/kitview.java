package com.jcdesimp.canvaskits.interfaceview;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class KitView {

    private Player player;

    //Should be divisible by 9
    private int viewHeight;
    private ArrayList<ViewButton> buttons;



    public KitView(Player p) {
        this.player = p ;
    }

    public void showUI() {

    }

    public void closeView() {

    }
}
