package de.adam.cbsystemv1.commands;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.CustomItemManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("customitem") || command.getName().equalsIgnoreCase("ci")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission(Permissions.creatcustomitemcommand)) {
                    CustomItemManager itemManager = new CustomItemManager();
                    if(args.length < 2) {
                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcommandusage);
                    }else if(args.length == 2 && args[0].equalsIgnoreCase("create")) {
                        if(args[1].equalsIgnoreCase("Zocker") || args[1].equalsIgnoreCase("ZockerRang") || args[1].equalsIgnoreCase("ZockerItem")) {
                            if(player.hasPermission(Permissions.createzockeritem)) {
                                ItemStack giveitem = itemManager.createZockerRangItem("§dZocker§5Rang", 1, Material.NETHERITE_CHESTPLATE);
                                player.getInventory().addItem(giveitem);
                                player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                            }
                        }
                    }else if (args.length == 3 && args[0].equalsIgnoreCase("create")) {
                        try {
                            if(args[1].equalsIgnoreCase("Zocker") || args[1].equalsIgnoreCase("ZockerRang") || args[1].equalsIgnoreCase("ZockerItem")) {
                                if(player.hasPermission(Permissions.createzockeritem)) {
                                    int amount = Integer.valueOf(args[2]);
                                    ItemStack giveitem = itemManager.createZockerRangItem("§dZocker§5Rang", amount, Material.NETHERITE_CHESTPLATE);
                                    player.getInventory().addItem(giveitem);
                                    player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                                }
                            }
                        }catch (NumberFormatException e) {
                            player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.usenumber);
                        }
                    }else  player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcommandusage);
                }else player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
            }else sender.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.onlyplayeruse);
        }
        return false;
    }
}
