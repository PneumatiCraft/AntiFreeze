package com.pneumaticraft.antifreeze.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class AFIceCommand extends AFCommand {

    public AFIceCommand(JavaPlugin plugin) {
        super(plugin);

        this.setName("Ice");
        this.setArgRange(0, 1);
        this.setCommandUsage("/ice [enable|disable]");
        this.addCommandExample("/ice");
        this.addCommandExample("/ice enable");
        this.setPermission("antifreeze.ice.use", "Use the /ice command", PermissionDefault.TRUE);
        this.addKey("ice");
    }

    public void runCommand(CommandSender sender, List<String> args) {
        if(args.size() == 0) {
            sender.sendMessage(ChatColor.AQUA + "Ice growth is currently " + (this.getPlugin().isIceEnabled() ? "enabled" : "disabled") + "!");
        } else {
            String action = args.get(0).toLowerCase();

            if(!action.equals("enable") && !action.equals("disable")) {
                sender.sendMessage(ChatColor.RED + "You must specify 'enable' or 'disable' when toggling ice!");
            }

            if(!this.getPlugin().getPermissionsHandler().hasPermission(sender, "antifreeze.ice." + action, false)) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to " + action + " + ice!");
            }

            if(action.equals("enable")) {
                this.getPlugin().enableIce();
                sender.sendMessage(ChatColor.AQUA + "You have enabled ice growth!");
            } else {
                this.getPlugin().disableIce();
                sender.sendMessage(ChatColor.AQUA + "You have disabled ice growth!");
            }
        }
    }
    
}
