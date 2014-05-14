package com.jcdesimp.canvaskits.kitstruct.kitactions;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * File created by jcdesimp on 5/14/14.
 */
public class EffectAction implements KitAction {

    PotionEffect effect;


    /**
     * Constuctor
     * @param effect to be done
     */
    public EffectAction(PotionEffect effect) {
        this.effect = effect;
    }


    /**
     * Applies an affect to a player
     * @param player to do the effec on
     */
    @Override
    public void call(Player player) {
        effect.apply(player);
    }



}
