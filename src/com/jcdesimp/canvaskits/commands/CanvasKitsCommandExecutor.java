package com.jcdesimp.canvaskits.commands;

import com.jcdesimp.canvaskits.CanvasKits;
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

        if (args.length > 0) {
            if(args[0].equalsIgnoreCase("reload")) {
                if(sender.hasPermission("canvaskits.admin.reload")){
                    plugin.onReload();
                    sender.sendMessage(ChatColor.YELLOW+"CanvasKits reloaded!");
                    return true;
                }

            }
        }
        sender.sendMessage(ChatColor.DARK_GREEN+"--|| CanvasKits "+plugin.getDescription().getVersion()+" Created by "
                + ChatColor.BLUE+"Jcdesimp"+ChatColor.DARK_GREEN+" ||--");
        return true;
        //return false;
    }



}
