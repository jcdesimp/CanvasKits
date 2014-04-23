package com.jcdesimp.canvaskits;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

/**
 * File created by jcdesimp on 4/22/14.
 */
public class CanvasKits extends JavaPlugin {

    private CanvasKits plugin;

    @Override
    public void onEnable() {
        plugin = this;



        getLogger().info("Created by Jcdesimp!");

        //// CONFIG FILE MANAGEMENT ///

        Map<String,Object> oldConfig = getConfig().getValues(true);
        //Generates new config file if not present
        saveDefaultConfig();
        //String header = getConfig().options().header();
        FileConfiguration config = getConfig();


        // checks for missing entries and applies new ones
        for (Map.Entry<String, Object> entry : config.getDefaults().getValues(true).entrySet()) {
            if(oldConfig.containsKey(entry.getKey())) {
                config.set(entry.getKey(),oldConfig.get(entry.getKey()));
            } else {
                config.set(entry.getKey(), entry.getValue());
            }

        }

        saveConfig();

        ////////////////////////////////


    }

}
