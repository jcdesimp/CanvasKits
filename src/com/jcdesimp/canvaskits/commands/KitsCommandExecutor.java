package com.jcdesimp.canvaskits.commands;

import com.jcdesimp.canvaskits.CanvasKits;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * File created by jcdesimp on 5/17/14.
 */
public class KitsCommandExecutor implements CommandExecutor {
    CanvasKits plugin;

    public KitsCommandExecutor(CanvasKits plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return showKitsView(sender, label);
        //return false;
    }

    private boolean showKitsView(CommandSender sender, String label) {
        if(sender instanceof Player){
            plugin.getViewManager().addView((Player)sender);
        }
        return true;
    }

}
