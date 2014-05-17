package com.jcdesimp.canvaskits.interfaceview;

import com.jcdesimp.canvaskits.kitstruct.ConcreteKit;
import com.jcdesimp.canvaskits.kitstruct.Kit;
import com.jcdesimp.canvaskits.kitstruct.KitCategory;
import com.jcdesimp.canvaskits.persistantData.Cooldown;
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
        kv.getPlayer().playSound(kv.getPlayer().getLocation(), Sound.CLICK, 1L, 1L);
        if(kit instanceof KitCategory) {
            kv.changeView((KitCategory) kit);
        } else if( kit instanceof ConcreteKit) {
            //System.out.println("Is concrete...");
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
                } else {
                    ((ConcreteKit) kit).activate(kv.getPlayer());
                    kv.getPlayer().sendMessage(ChatColor.GREEN+"Kit received!");

                }
            } else {
                //System.out.println("gonna activate it");
                ((ConcreteKit) kit).activate(kv.getPlayer());
                kv.getPlayer().sendMessage(ChatColor.GREEN+"Kit received!");

            }


            kv.closeView();
        }
    }

    public ItemStack getIcon() {
        ItemStack icon = new ItemStack(itm.clone());
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+kit.getDisplayName());
        ArrayList<String> lore = new ArrayList<String>(kit.getDescription());
        if(kit instanceof KitCategory){
            //doNothing
        } else if( kit instanceof ConcreteKit && ((ConcreteKit) kit).getCooldown() > 0) {
            long cooldown = ((ConcreteKit) kit).getPCooldown(kv.getPlayer());
            if( cooldown > System.currentTimeMillis()) {
                lore.add(ChatColor.RESET+""+ChatColor.YELLOW+"On Cooldown");
            } else {
                lore.add(ChatColor.RESET+""+ChatColor.GREEN+"Available");
            }
        } else {
            lore.add(ChatColor.RESET+""+ChatColor.GREEN+"Available");
        }


        meta.setLore(lore);
        icon.setItemMeta(meta);

        return icon;
    }
}
