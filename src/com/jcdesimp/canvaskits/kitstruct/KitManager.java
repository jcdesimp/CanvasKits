package com.jcdesimp.canvaskits.kitstruct;

import com.jcdesimp.canvaskits.CanvasKits;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * File created by jcdesimp on 5/10/14.
 */
public class KitManager {
    private ArrayList<Kit> headKits;
    private CanvasKits plugin;


    public KitManager(CanvasKits plugin) {
        this.headKits = new ArrayList<Kit>();
    }

    public void addKit(Kit k) {
        headKits.add(k);
    }

    public ArrayList<Kit> getHeadKits() {
        return headKits;
    }
}
