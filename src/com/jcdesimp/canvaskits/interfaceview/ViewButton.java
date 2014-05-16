package com.jcdesimp.canvaskits.interfaceview;

import com.jcdesimp.canvaskits.kitstruct.ConcreteKit;
import com.jcdesimp.canvaskits.kitstruct.Kit;
import com.jcdesimp.canvaskits.kitstruct.KitCategory;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

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
            ((ConcreteKit) kit).activate(kv.getPlayer());

            kv.closeView();
        }
    }

    public ItemStack getIcon() {
        ItemMeta meta = itm.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+kit.getDisplayName());
        meta.setLore(kit.getDescription());
        itm.setItemMeta(meta);

        return itm;
    }
}
