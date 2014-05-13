package com.jcdesimp.canvaskits.configuration;

import com.jcdesimp.canvaskits.CanvasKits;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

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
        CustomConfig newconf = new CustomConfig(plugin, filename);
        configs.put(filename, newconf);

        /*Map<String,Object> oldConfig = newconf.getConfig().getValues(true);
        //Generates new config file if not present
        newconf.saveDefaultConfig();
        FileConfiguration config = newconf.getConfig();


        // checks for missing entries and applies new ones
        for (Map.Entry<String, Object> entry : config.getDefaults().getValues(true).entrySet()) {
            if(oldConfig.containsKey(entry.getKey())) {
                config.set(entry.getKey(),oldConfig.get(entry.getKey()));
            } else {
                config.set(entry.getKey(), entry.getValue());
            }

        }

        newconf.saveConfig();*/
    }

}
