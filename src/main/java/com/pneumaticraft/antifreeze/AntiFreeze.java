package com.pneumaticraft.antifreeze;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.pneumaticraft.commandhandler.CommandHandler;

class AntiFreeze extends JavaPlugin {
    public static final String LOG_PREFIX = "[AntiFreeze] ";
    public static final Logger LOG = Logger.getLogger("Minecraft");

    private CommandHandler commandHandler;

    private boolean iceEnabled = false;

    @Override
    public void onEnable() {
        LOG.info(LOG_PREFIX + "Enabled!");

        this.commandHandler = new CommandHandler(this, new AFPermissionsHandler(this));
    }

    @Override
    public void onDisable() {
        LOG.info(LOG_PREFIX + "Disabled!");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        List<String> keyPath = new ArrayList<String>();
        keyPath.add(command.getLabel().toLowerCase());
        for(int i = 0; i < args.length; i++) {
            keyPath.add(args[i]);
        }
        return this.commandHandler.locateAndRunCommand(sender, keyPath);
    }

    public boolean isIceEnabled() {
        return iceEnabled;
    }

    public void enableIce() {
        this.iceEnabled = true;
    }

    public void disableIce() {
        this.iceEnabled = false;
    }
}
