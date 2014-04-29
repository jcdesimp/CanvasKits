package com.jcdesimp.canvaskits;

import com.avaje.ebean.EbeanServer;
import com.jcdesimp.canvaskits.persistantData.DBVersion;
import com.jcdesimp.canvaskits.persistantData.MyDatabase;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * File created by jcdesimp on 4/22/14.
 */
public class CanvasKits extends JavaPlugin {

    private CanvasKits plugin;
    private MyDatabase database;

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


        // Database creation, configuration, and maintenance.
        setupDatabase();
        //getLogger().info(getDescription().getName() + ": Created by Jcdesimp");
        getLogger().info("Created by Jcdesimp!");

        //Plugin Metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }



        // Command Executor
        getCommand("canvaskits").setExecutor(new CanvasKitsCommandExecutor(this));



        verifyDatabaseVersion();


    }


    /*
     * ***************************
     *       Database Stuff
     * ***************************
     */


    private void setupDatabase() {
        Configuration config = getConfig();

        database = new MyDatabase(this) {
            protected java.util.List<Class<?>> getDatabaseClasses() {
                List<Class<?>> list = new ArrayList<Class<?>>();
                list.add(DBVersion.class);

                return list;
            };
        };

        database.initializeDatabase(
                config.getString("database.driver","org.sqlite.JDBC"),
                config.getString("database.url","jdbc:sqlite:{DIR}{NAME}.db"),
                config.getString("database.username","bukkit"),
                config.getString("database.password","walrus"),
                config.getString("database.isolation","SERIALIZABLE"),
                config.getBoolean("database.logging", false),
                config.getBoolean("database.rebuild", false)
        );

        config.set("database.rebuild", false);

    }


    @Override
    public EbeanServer getDatabase() {
        return database.getDatabase();
    }

    public void verifyDatabaseVersion() {
        int CURRENT_VERSION = 1;
        if (this.getDatabase().find(DBVersion.class).where().eq("identifier","version").findUnique() == null) {
            DBVersion vUpdate = new DBVersion();
            vUpdate.setIdentifier("version");
            vUpdate.setIntData(1);
            this.getDatabase().save(vUpdate);
        }
        int currVersion = this.getDatabase().find(DBVersion.class).where().eq("identifier","version").findUnique().getIntData();
        if(currVersion < CURRENT_VERSION){
            this.getLogger().info("Database outdated!");
        }
    }

}
