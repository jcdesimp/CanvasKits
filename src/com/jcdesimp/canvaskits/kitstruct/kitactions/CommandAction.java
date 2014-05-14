package com.jcdesimp.canvaskits.kitstruct.kitactions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * File created by jcdesimp on 5/14/14.
 */
public class CommandAction implements KitAction {

    String command;
    String commandType;


    public CommandAction(String commandType, String command) {
        this.commandType = commandType;
        this.command = command;
    }

    @Override
    public void call(Player player) {
        command = command.replaceAll("@p",player.getName());
        if (commandType.equalsIgnoreCase("Player")){      //Player command
            player.performCommand(command);
        } else {   //Console command
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),command);
        }
    }
}
