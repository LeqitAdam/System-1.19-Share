package de.adam.commands;

import de.adam.main.ZockerWorldCBV1;
import de.adam.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreatecustomItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("createcustomitem") || command.getName().equalsIgnoreCase("cci") || command.getName().equalsIgnoreCase("createitem")) {
            if(sender instanceof Player) {

            }else sender.sendMessage(ZockerWorldCBV1.prefix + Messages.onlyplayeruse);
        }
        return false;
    }
}
