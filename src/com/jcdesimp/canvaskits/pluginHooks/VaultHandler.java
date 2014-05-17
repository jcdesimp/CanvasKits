package com.jcdesimp.canvaskits.pluginHooks;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * File created by jcdesimp on 3/15/14.
 */
public class VaultHandler {
    private Economy economy = null;
    private JavaPlugin plugin;

    public VaultHandler(JavaPlugin p) {
        this.plugin = p;
        setupEconomy();
    }


    private boolean setupEconomy(){

        RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }

    public boolean hasEconomy(){

        if(economy == null){
            return false;
        }
        return true;
    }

    public boolean canAfford(Player p, double amt){
        if(economy.getBalance(p.getName()) >= amt){
            return true;

        }
        return false;
    }

    public boolean chargeCash(Player p, double amt){
        if(canAfford(p,amt)){
            EconomyResponse r = economy.withdrawPlayer(p.getName(), amt);
            return true;
        }
        return false;
    }

    public boolean giveCash(Player p, double amt){
        EconomyResponse r = economy.depositPlayer(p.getName(),amt);
        return true;
    }

    public String formatCash(double amt){
        return economy.format(amt);
    }


}
