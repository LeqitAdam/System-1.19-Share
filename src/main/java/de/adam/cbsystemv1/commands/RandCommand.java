package de.adam.cbsystemv1.commands;

import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.InventoryManager;
import de.adam.cbsystemv1.methods.PlotManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("rand")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission(Permissions.randcommand)) {
                    InventoryManager inv = new InventoryManager();
                    player.openInventory(inv.getRandInv(player));
                }else player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
            }else sender.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.onlyplayeruse);
        }
        return false;
    }
}
