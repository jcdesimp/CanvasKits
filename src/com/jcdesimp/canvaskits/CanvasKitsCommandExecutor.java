package com.jcdesimp.canvaskits;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * File created by jcdesimp on 4/23/14.
 */
public class CanvasKitsCommandExecutor implements CommandExecutor {
    private CanvasKits plugin; //pointer to main class
    public CanvasKitsCommandExecutor(CanvasKits plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0){
            showKitsView(sender, label);
        } else {

        }
        return false;
    }


    private boolean showKitsView(CommandSender sender, String label) {
        if(sender instanceof Player){

        }
        return true;
    }
}
