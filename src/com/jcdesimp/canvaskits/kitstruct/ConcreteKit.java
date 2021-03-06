package com.jcdesimp.canvaskits.kitstruct;

import com.jcdesimp.canvaskits.CanvasKits;
import com.jcdesimp.canvaskits.kitstruct.kitactions.KitAction;
import com.jcdesimp.canvaskits.persistantData.Cooldown;
import com.jcdesimp.canvaskits.pluginHooks.VaultHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class ConcreteKit implements Kit {
    String displayName;
    ArrayList<String> description;
    ItemStack displayItem;
    ArrayList<KitAction> kitActions;
    String uniqueName;


    int price = 0;

    //cooldown in seconds
    int cooldown = 0;

    public ConcreteKit(String displayName, List<String> desc, ItemStack displayItem, String uName) {
        this.displayName = displayName;
        this.uniqueName = uName;
        this.description = new ArrayList<String>();
        for(String s : desc) {
            description.add(ChatColor.RESET+s);
        }
        this.displayItem = displayItem;
        this.kitActions = new ArrayList<KitAction>();
    }


    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCooldown() {
        return cooldown*1000;
    }

    /**
     * gets cooldown remainder
     * @param p
     * @return
     */
    public long getPCooldown(Player p) {
        return Cooldown.getCooldown(uniqueName,p);
    }

    public void addKitAction(KitAction ka) {
        if(ka != null) {
            kitActions.add(ka);
        }

    }

    public void activate(Player p) {
        for(KitAction k: kitActions){
            k.call(p);

        }
        Cooldown.setPCooldown(this,p);
        if(price > 0) {
            VaultHandler vh = ((CanvasKits) Bukkit.getServer().getPluginManager().getPlugin("CanvasKits")).getvHandler();
            vh.chargeCash(p,price);
            p.sendMessage(ChatColor.YELLOW+"You have been charged "+vh.formatCash(price)+".");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean pHasPerm(Player p) {
        if(p.hasPermission("canvaskits.kits."+uniqueName) || p.hasPermission("canvaskits.kits.*")){
            return true;
        }
        String permTraverse = uniqueName;
        while (permTraverse.contains(".")) {
            permTraverse = permTraverse.substring(0,permTraverse.lastIndexOf('.'));
            //System.out.println(permTraverse+".*");
            if(p.hasPermission("canvaskits.kits."+permTraverse+".*")){
                return true;
            }
        }

        return false;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public ArrayList<String> getDescription() {
        return description;
    }

    @Override
    public ItemStack getDisplayItem() {
        return displayItem;
    }

    @Override
    public String getUniqueName() {
        return uniqueName;
    }
}
