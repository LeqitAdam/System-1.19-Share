package de.adam.cbsystemv1.commands;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.Warps;
import com.earth2me.essentials.commands.EssentialsCommand;
import com.earth2me.essentials.commands.WarpNotFoundException;
import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.listener.InventoryHandler;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.InventoryManager;
import de.adam.globalsystemv1.main.GlobalSystemSpigot;
import net.ess3.api.InvalidWorldException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("warp")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                InventoryHandler warpHandler = new InventoryHandler();
                if(player.hasPermission(Permissions.warpcommand)) {
                    if(args.length == 0) {
                        InventoryManager inventorys = new InventoryManager();
                        player.openInventory(inventorys.getWarpInv());
                    }else if(args.length == 1) {
                        try {
                            player.teleport(warpHandler.getWarpLocation(args[0]));
                            player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpcommandteleported1 + args[0] + Messages.warpcommandteleported2);
                        } catch (WarpNotFoundException e) {
                            player.closeInventory();
                            player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpnotfound);
                            throw new RuntimeException(e);
                        } catch (InvalidWorldException e) {
                            throw new RuntimeException(e);
                        }
                    }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpcommandusage);
                }else player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
            }else sender.sendMessage(GlobalSystemSpigot.prefix + de.adam.globalsystemv1.files.Messages.onlyplayeruse);
        }
        return false;
    }
}
