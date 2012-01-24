package com.pneumaticraft.antifreeze;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.pneumaticraft.commandhandler.PermissionsInterface;

/**
 * Utility class to handle permissions checks for AntiFreeze. Implements basic
 * functionality required by CommandHandler for permissions checking and
 * command dispatch. Interfaces with both SuperPerms-based systems and old
 * Permissions plugins.
 */
public class AFPermissionsHandler implements PermissionsInterface {

    /**
     * AntiFreeze instance backing this permissions handler.
     */
    private AntiFreeze plugin;

    /**
     * Create a new permissions handler for the given AntiFreeze instance.
     *
     * @param af The AntiFreeze instance for which to handle permissions.
     */
    public AFPermissionsHandler(AntiFreeze af) {
        this.plugin = af;
    }

    @Override
    public boolean hasPermission(CommandSender sender, String node, boolean isOpRequired) {
        if (!(sender instanceof Player)) {
            return true;
        } else {
            Player player = (Player) sender;
            if (player.hasPermission(node)) {
                return true;
            } else if (isOpRequired) {
                return player.isOp();
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean hasAnyPermission(CommandSender sender, List<String> allPermissionStrings, boolean opRequired) {
        for (String node : allPermissionStrings) {
            if (this.hasPermission(sender, node, opRequired)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasAllPermission(CommandSender sender, List<String> allPermissionStrings, boolean opRequired) {
        for (String node : allPermissionStrings) {
            if (!this.hasPermission(sender, node, opRequired)) {
                return false;
            }
        }
        return true;
    }

}
