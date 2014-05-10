package com.jcdesimp.canvaskits.kitstruct;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * File created by jcdesimp on 5/10/14.
 */
public class KitManager {
    private HashSet<String> registeredKits;
    private ArrayList<Kit> kitStruct;

    public KitManager() {
        this.registeredKits = new HashSet<String>();
        this.kitStruct = new ArrayList<Kit>();
    }
}
