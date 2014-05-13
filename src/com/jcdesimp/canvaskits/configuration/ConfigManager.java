package com.jcdesimp.canvaskits.configuration;

import com.jcdesimp.canvaskits.CanvasKits;

import java.util.HashMap;

/**
 * File created by jcdesimp on 5/12/14.
 */
public class ConfigManager {

    HashMap<String, CustomConfig> configs;
    CanvasKits plugin ;

    public ConfigManager(CanvasKits plugin) {
        this.configs = new HashMap<String, CustomConfig>();
        this.plugin = plugin;
    }

    public CustomConfig getConfig(String filename) {
        if(configs.containsKey(filename)){
            return configs.get(filename);
        }
        return null;
    }

    public void addConfig(String filename) {
        configs.put(filename, new CustomConfig(plugin, filename));
    }

}
