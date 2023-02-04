package de.adam.commands;

import de.adam.main.PlaycenSystemV2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Werkbank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("system.craft") || p.hasPermission("system.wb")) {
                if(cmd.getName().equalsIgnoreCase("wb") || cmd.getName().equalsIgnoreCase("werkbank")) {
                    if(args.length == 0) {
                        p.openWorkbench(p.getLocation(), true);
                    }else
                        p.sendMessage(PlaycenSystemV2.pre + " §cBitte benutze: §7/wb");
                }else
                    p.sendMessage(PlaycenSystemV2.pre + " §cBitte benutze: §7/wb");
            }else
                p.sendMessage(PlaycenSystemV2.pre + PlaycenSystemV2.noperm);
        }else
            sender.sendMessage(PlaycenSystemV2.pre + " §cNur Spieler können diesen Befehl nutzen!");

        return false;
    }
}
