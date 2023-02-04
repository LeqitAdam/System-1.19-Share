package de.adam.commands;

import de.adam.main.PlaycenSystemV2;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Clearchat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("system.chatclear")) {
                if(cmd.getName().equalsIgnoreCase("chatclear") || cmd.getName().equalsIgnoreCase("cc") || cmd.getName().equalsIgnoreCase("clearchat")) {
                    for (int i = 0; i < 200; i++) {
                        for (Player t : Bukkit.getOnlinePlayers()) {

                            // ab hier sind die Bypass - Rechte

                            if (t.hasPermission("system.chatclear.bypass")) {
                                continue;
                            }
                            t.sendMessage("");
                        }
                    }
                    Bukkit.broadcastMessage(PlaycenSystemV2.pre + " §7Der Chat wurde von §a" + p.getName() + " §7geleert!");
                }else
                    p.sendMessage(PlaycenSystemV2.pre + " §cBitte benutze: §7/chatclear");
            } else {
                p.sendMessage(PlaycenSystemV2.pre + PlaycenSystemV2.noperm);
            }
        } else
            sender.sendMessage(PlaycenSystemV2.pre + " §cNur Spieler können diesen Befehl nutzen!");
        return true;
    }
}
