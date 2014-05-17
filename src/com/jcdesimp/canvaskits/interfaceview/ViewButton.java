package com.jcdesimp.canvaskits.interfaceview;

import com.jcdesimp.canvaskits.CanvasKits;
import com.jcdesimp.canvaskits.kitstruct.ConcreteKit;
import com.jcdesimp.canvaskits.kitstruct.Kit;
import com.jcdesimp.canvaskits.kitstruct.KitCategory;
import com.jcdesimp.canvaskits.persistantData.Cooldown;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;

/**
 * File created by jcdesimp on 5/10/14.
 */
public class ViewButton {
    Kit kit;
    KitView kv;
    ItemStack itm;

    public ViewButton(Kit kit, KitView kv) {
        this.kit = kit;
        this.kv = kv;
        this.itm = kit.getDisplayItem();
    }

    public void onClick() {
        CanvasKits plugin = (CanvasKits)Bukkit.getPluginManager().getPlugin("CanvasKits");
        kv.getPlayer().playSound(kv.getPlayer().getLocation(), Sound.CLICK, 1L, 1L);
        if(kit instanceof KitCategory) {
            kv.changeView((KitCategory) kit);
        } else if( kit instanceof ConcreteKit) {
            //System.out.println("Is concrete...");
            kv.closeView();
            if(!((ConcreteKit) kit).pHasPerm(kv.getPlayer())){
                kv.getPlayer().sendMessage(ChatColor.RED+"You are not allowed to use that kit.");
                return;
            }
            if(plugin.hasVault() && plugin.getvHandler().hasEconomy() && ((ConcreteKit) kit).getPrice() > 0) {
                if(!plugin.getvHandler().canAfford(kv.getPlayer(), ((ConcreteKit) kit).getPrice())){
                    kv.getPlayer().sendMessage(ChatColor.RED+"That kit costs "+plugin.getvHandler().formatCash(((ConcreteKit) kit).getPrice())+".");
                    return;
                }
            }
            if (((ConcreteKit) kit).getCooldown() > 0) {
                //System.out.println("cooldown more than 0");
                long cooldown = ((ConcreteKit) kit).getPCooldown(kv.getPlayer());
                if( cooldown > System.currentTimeMillis()) {
                    System.out.println("More than CurrMilis");
                    String message = ChatColor.RED+"You must wait ";
                    long timeLeft = cooldown-System.currentTimeMillis();
                    long secondsLeft = timeLeft/1000;
                    long minutesLeft = secondsLeft/60;
                    long hoursLeft = minutesLeft/60;
                    long daysLeft = hoursLeft/24;

                    if(daysLeft>0) {
                        message+="" + daysLeft + " more day(s).";
                    } else if(hoursLeft > 0) {
                        message+="" + hoursLeft + " more hour(s).";
                    } else if(minutesLeft > 0) {
                        message+="" + minutesLeft + " more minute(s).";
                    }  else if(secondsLeft > 0) {
                        message+="" + secondsLeft + " more second(s).";
                    }
                    kv.getPlayer().sendMessage(message);
                    return;
                }
            }
            //System.out.println("gonna activate it");
            ((ConcreteKit) kit).activate(kv.getPlayer());
            kv.getPlayer().sendMessage(ChatColor.GREEN+"Kit received!");



        }
    }

    public ItemStack getIcon() {
        ItemStack icon = new ItemStack(itm.clone());
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+kit.getDisplayName());
        ArrayList<String> lore = new ArrayList<String>(kit.getDescription());

        if(kit instanceof KitCategory){
            //doNothing
        } else if( kit instanceof ConcreteKit) {
            CanvasKits plugin = (CanvasKits)Bukkit.getPluginManager().getPlugin("CanvasKits");
            String status = ChatColor.RESET+""+ChatColor.GREEN+"Available";
            //Add additional info

            //Permissions
            if(!((ConcreteKit) kit).pHasPerm(kv.getPlayer())){
                if(status.equals(ChatColor.RESET+""+ChatColor.GREEN+"Available")) {
                    status = ChatColor.RESET+""+ChatColor.RED+"Restricted";
                }
            }

            //Price
            if(plugin.hasVault() && plugin.getvHandler().hasEconomy() && ((ConcreteKit) kit).getPrice() > 0){
                lore.add(ChatColor.RESET+""+ChatColor.GRAY+""+ChatColor.ITALIC+"Price: "+ChatColor.YELLOW+plugin.getvHandler().formatCash(((ConcreteKit) kit).getPrice()));
                if(status.equals(ChatColor.RESET+""+ChatColor.GREEN+"Available") && !plugin.getvHandler().canAfford(kv.getPlayer(), ((ConcreteKit) kit).getPrice())) {
                    status = ChatColor.YELLOW + "Unaffordable";
                }
            }


            //Cooldown
            if(((ConcreteKit) kit).getCooldown() > 0){
                long cooldown = ((ConcreteKit) kit).getPCooldown(kv.getPlayer());
                String message = ChatColor.GRAY+""+ChatColor.ITALIC+"Cooldown: "+ChatColor.DARK_PURPLE;
                long timeLeft = ((ConcreteKit) kit).getCooldown();
                long secondsLeft = timeLeft/1000;
                long minutesLeft = secondsLeft/60;
                long hoursLeft = minutesLeft/60;
                long daysLeft = hoursLeft/24;

                if(daysLeft>0) {
                    message+="" + daysLeft + " day(s)";
                } else if(hoursLeft > 0) {
                    message+="" + hoursLeft + " hour(s)";
                } else if(minutesLeft > 0) {
                    message+="" + minutesLeft + " minute(s)";
                }  else if(secondsLeft > 0) {
                    message+="" + secondsLeft + " second(s)";
                }
                lore.add(message);
                if(status.equals(ChatColor.RESET+""+ChatColor.GREEN+"Available") && cooldown > System.currentTimeMillis()) {
                    status = ChatColor.RESET+""+ChatColor.YELLOW+"On Cooldown";
                }
            }
            lore.add(status);

        }


        meta.setLore(lore);
        icon.setItemMeta(meta);

        return icon;
    }
}
