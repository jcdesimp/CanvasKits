package com.jcdesimp.canvaskits.persistantData;

import com.avaje.ebean.validation.NotNull;
import com.jcdesimp.canvaskits.CanvasKits;
import com.jcdesimp.canvaskits.kitstruct.ConcreteKit;
import com.jcdesimp.canvaskits.kitstruct.Kit;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * File created by jcdesimp on 5/2/14.
 */

//@SuppressWarnings("UnusedDeclaration")
@Entity
@Table(name="ck_cooldown")
public class Cooldown {

    @Id
    private int id;
    @NotNull
    private String kitName;
    @NotNull
    private String playerUuid ;
    @NotNull
    private long endTime;

    public static Cooldown getNewCooldown(String kName, String playerUUID, long cdEndTime) {
        Cooldown coold = new Cooldown();
        coold.setKitName(kName);
        coold.setPlayerUuid(playerUUID);
        coold.setEndTime(cdEndTime);
        return coold;
    }

    public static void removeCooldown(Cooldown c) {
        CanvasKits plugin = (CanvasKits)Bukkit.getServer().getPluginManager().getPlugin("CanvasKits");
        plugin.getDatabase().delete(c);
    }

    public static void setPCooldown(Kit k, Player p) {
        CanvasKits plugin = (CanvasKits)Bukkit.getServer().getPluginManager().getPlugin("CanvasKits");
        Cooldown c = plugin.getDatabase().find(Cooldown.class).where().eq("kitName",k.getUniqueName()).eq("playerUuid",p.getUniqueId().toString()).findUnique();
        if(c != null){
            plugin.getDatabase().delete(c);
        }
        //Cooldown nc = getNewCooldown(k.getUniqueName(), p.getUniqueId().toString(),
        //        System.currentTimeMillis()+(((ConcreteKit)k).getCooldown()*1000));

        Cooldown nc = new Cooldown();
        nc.setKitName(k.getUniqueName());
        nc.setEndTime(System.currentTimeMillis()+(((ConcreteKit)k).getCooldown()));
        nc.setPlayerUuid(p.getUniqueId().toString());

        plugin.getDatabase().save(nc);
        //plugin.getDatabase().save( Cooldown.getNewCooldown(k.getUniqueName(), p.getUniqueId().toString(),
          //      System.currentTimeMillis()+(((ConcreteKit)k).getCooldown()*1000)));

    }

    public static long getCooldown(String kName, Player p) {
       CanvasKits plugin = (CanvasKits)Bukkit.getServer().getPluginManager().getPlugin("CanvasKits");
       Cooldown c = plugin.getDatabase().find(Cooldown.class).where().eq("kitName",kName).eq("playerUuid",p.getUniqueId().toString()).findUnique();
       if(c == null){
           return 0;
       }
       return c.getEndTime();
    }



    public void setId(int id) {
        this.id = id;
    }



    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public int getId() {
        return id;
    }

    public String getKitName() {
        return kitName;
    }

    public String getPlayerUuid() {
        return playerUuid;
    }

    public void setPlayerUuid(String playerUuid) {
        this.playerUuid = playerUuid;
    }

    public long getEndTime() {
        return endTime;
    }
}
