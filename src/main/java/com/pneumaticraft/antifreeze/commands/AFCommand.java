package com.pneumaticraft.antifreeze.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.pneumaticraft.antifreeze.AntiFreeze;
import com.pneumaticraft.commandhandler.Command;

/**
 * Abstract parent class for all executable commands in AntiFreeze. Every command
 * handled by AntiFreeze is a concrete subclass of AFCommand, which itself subclasses
 * CommandHandler's Command class.
 */
public abstract class AFCommand extends Command {

    /**
     * Instantiate a command backed by the given plugin. The plugin is used
     * in subclasses for various queries back into Bukkit.
     *
     * @param plugin The plugin used for Bukkit calls in concrete command
     * subclasses.
     */
    public AFCommand(JavaPlugin plugin) {
        super(plugin);
    }

    /**
     * Get this AFCommand's plugin. Casts the plugin passed in the constructor
     * to an instance of AntiFreeze.
     *
     * @see #AFCommand(JavaPlugin)
     *
     * @return The AntiFreeze plugin instance handling this AFCommand.
     */
    public AntiFreeze getPlugin() {
        return (AntiFreeze) this.plugin;
    }

    /**
     * Check if the sender of this AFCommand is a Player. If the sender is not a
     * Player, send an error message back.
     *
     * @param sender The CommandSender to check.
     * @return true if the sender is a Player; false otherwise.
     */
    public boolean checkPlayerSender(CommandSender sender) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.RED + "Command must be run in-game!");
            return false;
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Cannot verify command's sender!");
            return false;
        }
        return true;
    }

    @Override
    public void addKey(String key) {
        super.addKey(key);
    }

    @Override
    public void addKey(String key, int minArgs, int maxArgs) {
        super.addKey(key, minArgs, maxArgs);
    }
}
