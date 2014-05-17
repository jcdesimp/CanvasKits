package com.jcdesimp.canvaskits.kitstruct;

import com.jcdesimp.canvaskits.CanvasKits;
import com.jcdesimp.canvaskits.kitstruct.kitactions.CommandAction;
import com.jcdesimp.canvaskits.kitstruct.kitactions.EffectAction;
import com.jcdesimp.canvaskits.kitstruct.kitactions.ItemsAction;
import com.jcdesimp.canvaskits.kitstruct.kitactions.KitAction;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * File created by jcdesimp on 5/10/14.
 */
public class KitManager {
    private ArrayList<Kit> headKits;
    private CanvasKits plugin;

    /**
     * Constructor for KitManager
     * @param plugin main plugin
     */
    public KitManager(CanvasKits plugin) {

        this.headKits = new ArrayList<Kit>();
        this.plugin = plugin;
        parseKits();
    }


    /**
     * getter for the head kits
     * @return the head kits
     */
    public ArrayList<Kit> getHeadKits() {
        return headKits;
    }

    /**
     * Method to add all the head kits to the kit list
     */
    private void parseKits() {
        ConfigurationSection headSec = plugin.getConfigManager().getConfig("kits.yml").getConfig().getConfigurationSection("kits");
        for(String s : headSec.getKeys(false)) {
            Kit newKit = parseKit(headSec.getConfigurationSection(s), s);
            if(newKit != null){
                headKits.add(newKit);
            }
        }


    }

    /**
     * Recursive method to parse and create a new kit
     * @param cfg configurationSection mapping representing a kit
     * @return the newly created kit
     */
    private Kit parseKit( ConfigurationSection cfg, String uName ) {
        //System.out.println( cfg.toString() );
        Map<String, Object> vals = cfg.getValues(true);
        String displayName;
        ItemStack displayItem;
        List<String> desc = new ArrayList<String>();

        //check name
        if(vals.containsKey("name")){
            displayName = (String)vals.get("name");
        } else {
            displayName = "Unnamed";
        }
        //System.out.println("KitName: " + displayName);

        //check display item
        if(vals.containsKey("item")){
            displayItem = parseItem(cfg.getConfigurationSection("item"));
        } else {
            displayItem = new ItemStack(Material.STONE);
        }
        //System.out.println("KitItem: " + displayItem.toString());

        //check description
        if(vals.containsKey("description")) {
           desc = cfg.getStringList("description");
        }
        //System.out.println("Description: " + desc.toString());

        //is categrory
        if(vals.containsKey("kits")) {
            KitCategory k = new KitCategory(displayName, desc, displayItem, uName);
            for(String s : cfg.getConfigurationSection("kits").getKeys(false)) {
                k.addKit(parseKit(cfg.getConfigurationSection("kits." + s), uName+"."+s));
            }



            return k;
        } else {  //is concrete kit

            ConcreteKit k = new ConcreteKit(displayName, desc, displayItem, uName);
            if(vals.containsKey("actions")) {
                for(String s : cfg.getConfigurationSection("actions").getKeys(false)) {
                    k.addKitAction(parseAction(cfg.getConfigurationSection("actions." + s)));
                }

            }

            //set cooldown
            if(vals.containsKey("cooldown")){
                //System.out.println(vals.get("cooldown"));
                k.setCooldown(((Integer)vals.get("cooldown")));
            }


            return k;

        }

    }

    /**
     * Parses data for a kit action
     * @param cfg section to parse
     * @return KitAction
     */
    private KitAction parseAction(ConfigurationSection cfg) {
        Map<String,Object> vals = cfg.getValues(true);
        KitAction action;


        if(vals.containsKey("type")) {
            String actionType = (String) vals.get("type");
            //System.out.println("Type: " + actionType);

            //Determine what type of action it is
            if (actionType.equalsIgnoreCase("ITEMS") || actionType.equalsIgnoreCase("ITEM")) {
                if(vals.containsKey("item")){
                    action = new ItemsAction(parseItem(cfg.getConfigurationSection("item")));
                    return action;
                }
                return null;


            } else if (actionType.equalsIgnoreCase("EFFECT") || actionType.equalsIgnoreCase("POTION")) {
                if(vals.containsKey("effect")){

                    action = new EffectAction(parsePotion(cfg.getConfigurationSection("effect")));
                    return action;
                }
                return null;

            } else if (actionType.equalsIgnoreCase("COMMAND") || actionType.equalsIgnoreCase("COMMANDS")) {
                String cType = cfg.getString("command.type", "console");
                String cmd = cfg.getString("command.command","list");

                action = new CommandAction(cType, cmd);
                return action;

            }
        }
        return null;
    }


    /**
     * Parses thru to get a potion effect
     * @param cfg to parse thru
     * @return the potion effect
     */
    private PotionEffect parsePotion(ConfigurationSection cfg) {

        //set type
        PotionEffectType pType = PotionEffectType.getByName(cfg.getString("type","absorption").toUpperCase());

        //set duration
        int duration = cfg.getInt("duration", 5);


        //set level
        int level = cfg.getInt("level", 1);

        //set ambient
        boolean ambient = cfg.getBoolean("ambient",false);

        return new PotionEffect(pType, duration, level, ambient);
    }

    /**
     * parse through dat to create a new item
     * with enchantments, quantity, and damage value
     * @param cfg to parse through
     * @return the new ItemStack
     */
    private ItemStack parseItem( ConfigurationSection cfg ) {
        Map<String, Object> vals = cfg.getValues(true);
        ItemStack newItem;
        //check material
        newItem = new ItemStack(Material.getMaterial((cfg.getString("material","stone")).toUpperCase()));

        //check damage value

        newItem.setDurability((short)(cfg.getInt("damage",0)));

        //check quantity

        newItem.setAmount(cfg.getInt("amount",1));

        //enchantment parsing
        for ( String s : cfg.getStringList("enchantments")) {
            String[] parts = s.split(":");
            String enchant = parts[0];
            int level = 1;

            if(parts.length > 1){
                level = Integer.parseInt(parts[1]);
            }
            newItem.addUnsafeEnchantment(Enchantment.getByName(enchant.toUpperCase()), level);
        }


        //System.out.println("Item: "+ newItem.toString());
        return newItem;

        //ItemStack is = new ItemStack(Material.);
    }

}
