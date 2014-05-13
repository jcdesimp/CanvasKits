package com.jcdesimp.canvaskits.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;

/**
 * File created by jcdesimp on 5/12/14.
 */
public class CustomConfig {


    private String filename;
    private FileConfiguration customConfig = null;
    private File customConfigFile = null;
    private JavaPlugin plugin;


    /**
     * Constructor for a custom config
     * @param plugin that the config belongs to
     * @param filename of the config
     */
    public CustomConfig(JavaPlugin plugin, String filename){

        this.filename = filename;
        this.plugin = plugin;
        Map<String,Object> oldConfig = getConfig().getValues(true);
        //Generates new config file if not present
        saveDefaultConfig();
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


    }

    /**
     * reload the config from the filesystem
     */
    public void reloadConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), filename);
        }
        customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

        // Look for defaults in the jar
        InputStream defConfigStream = plugin.getResource(filename);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            customConfig.setDefaults(defConfig);
        }
    }

    /**
     * Get the configuration object
     * @return conciguration
     */
    public FileConfiguration getConfig() {
        if (customConfig == null) {
            reloadConfig();
        }
        return customConfig;
    }

    /**
     * save the current config
     */
    public void saveConfig() {
        if (customConfig == null || customConfigFile == null) {
            return;
        }
        try {
            getConfig().save(customConfigFile);
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
        }
    }

    /**
     * Save a default version of the config
     */
    public void saveDefaultConfig() {
        if (customConfigFile == null) {
            customConfigFile = new File(plugin.getDataFolder(), filename);
        }
        if (!customConfigFile.exists()) {
            plugin.saveResource(filename, false);
        }
    }


}
