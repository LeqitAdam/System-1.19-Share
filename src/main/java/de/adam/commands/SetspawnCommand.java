package de.adam.commands;

import de.adam.main.ZockerWorldCBV1;
import de.adam.files.Messages;
import de.adam.files.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetspawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            File config = new File("plugins/Lobby-Zockerworld", "config.yml");
            YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
            Player p = (Player) sender;
            if(p.hasPermission(Permissions.setspawncommand)){
                if(args.length == 0){
                    conf.set("Spawn.world", p.getLocation().getWorld().getName());
                    conf.set("Spawn.x", p.getLocation().getX());
                    conf.set("Spawn.y", p.getLocation().getY());
                    conf.set("Spawn.z", p.getLocation().getZ());
                    conf.set("Spawn.yaw", p.getLocation().getYaw());
                    conf.set("Spawn.pitch", p.getLocation().getPitch());
                    try {
                        conf.save(config);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(ZockerWorldCBV1.prefix + Messages.setspawnsucces);
                } else p.sendMessage(ZockerWorldCBV1.prefix + Messages.setspawncommandusage);
            }else p.sendMessage(ZockerWorldCBV1.prefix + Messages.noperm);
        }else sender.sendMessage(ZockerWorldCBV1.prefix + Messages.onlyplayeruse);
        return false;
    }
}
