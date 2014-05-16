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
        //System.out.println(command);
        //String cmd = command;
        String cmd = command.replaceAll("@p",player.getName());
        //System.out.println(cmd);
        if (commandType.equalsIgnoreCase("Player")){      //Player command
            player.performCommand(command);
        } else {   //Console command
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
        }
    }
}
