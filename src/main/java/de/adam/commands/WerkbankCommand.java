package de.adam.commands;

import de.adam.main.ZockerWorldCBV1;
import de.adam.utils.Messages;
import de.adam.utils.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WerkbankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission(Permissions.wbcommand)) {
                if(command.getName().equalsIgnoreCase("wb") || command.getName().equalsIgnoreCase("werkbank") || command.getName().equalsIgnoreCase("workbench")) {
                    if(args.length == 0) {
                        p.openWorkbench(p.getLocation(), true);
                    }else
                        p.sendMessage(ZockerWorldCBV1.prefix + Messages.wbcommandusage);
                }else p.sendMessage(ZockerWorldCBV1.prefix + Messages.wbcommandusage);
            }else p.sendMessage(ZockerWorldCBV1.prefix + Messages.noperm);
        }else sender.sendMessage(ZockerWorldCBV1.prefix + Messages.onlyplayeruse);
        return false;
    }
}
