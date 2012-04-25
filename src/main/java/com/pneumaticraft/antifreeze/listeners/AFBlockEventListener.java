package com.pneumaticraft.antifreeze.listeners;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockFormEvent;

import com.pneumaticraft.antifreeze.AntiFreeze;

public class AFBlockEventListener implements Listener {

    private AntiFreeze plugin;

    public AFBlockEventListener(AntiFreeze af) {
        this.plugin = af;
    }

    @EventHandler()
    public void blockForm(BlockFormEvent event) {
        if(event.getNewState().getType() == Material.ICE && this.plugin.isIceEnabled() == false) {
            event.setCancelled(true);
        }
    }

}
