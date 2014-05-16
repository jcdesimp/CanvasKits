package com.jcdesimp.canvaskits.interfaceview;

import com.jcdesimp.canvaskits.kitstruct.Kit;
import com.jcdesimp.canvaskits.kitstruct.KitCategory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.ArrayList;

/**
 * File created by jcdesimp on 5/9/14.
 */
public class KitView {

    private Player player;

    //Should be divisible by 9
    private int invSize = 27;
    private Inventory invView;
    private ArrayList<ViewButton> buttons;
    private KitView prevView;
    private int pageNum;
    private int currPage;
    ViewManager vm;



    public KitView(Player p, ViewManager vm, ArrayList<Kit> kits) {
        this.player = p ;
        this.vm = vm;
        this.buttons = new ArrayList<ViewButton>();
        this.pageNum = 0;
        this.currPage = 0;
        buildUI(kits);
        showUI();

    }
    public KitView(Player p, ViewManager vm, ArrayList<Kit> kits, KitView pv) {
        this.player = p ;
        this.vm = vm;
        this.buttons = new ArrayList<ViewButton>();
        this.pageNum = 0;
        this.currPage = 0;
        this.prevView = pv;
        buildUI(kits);
        showUI();

    }

    public void addButton(Kit k) {
        buttons.add(new ViewButton(k, this));
    }

    private void buildUI(ArrayList<Kit> kits){
        for(Kit k : kits){
            addButton(k);
        }
    }
    public void showUI() {
        if((buttons.size() > invSize) || (pageNum > 0)) {
            invSize = 36;
        }
        invView = Bukkit.createInventory(null, invSize, "CANVAS Kits");
        for(int i = 0; i<buttons.size(); i++){
            invView.setItem(i, buttons.get(i).getIcon());
        }
        player.openInventory(invView);
    }

    public void closeView() {
        vm.clearView(player);
        player.closeInventory();
    }

    public void changeView(KitCategory k) {
        vm.changeView(player, new KitView(player, vm, k.getKits(), this));
    }

    public void slotClicked(int slot) {
        if(slot>=buttons.size()){
            return;
        }
        buttons.get(slot).onClick();
        //System.out.println("Clicked slot: " + slot);
    }

    public Player getPlayer() {
        return player;
    }
}
