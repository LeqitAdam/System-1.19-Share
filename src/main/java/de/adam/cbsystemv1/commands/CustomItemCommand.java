package de.adam.cbsystemv1.commands;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.CustomItemManager;
import de.adam.cbsystemv1.methods.ItemManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
                            }else player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
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
                            }else if(args[1].equalsIgnoreCase("randperm") || args[1].equalsIgnoreCase("rand") || args[1].equalsIgnoreCase("randitem")) {
                                if(player.hasPermission(Permissions.createranditem)) {
                                    if(ItemManager.getMaterialFromStringAlternative(args[2].toUpperCase()) != null) {
                                        List<String> alleränder = getRandItems();
                                        if(alleränder.contains(args[2].toLowerCase())) {
                                            Material material = ItemManager.getMaterialFromStringAlternative(args[2].toUpperCase());
                                            String name = getName(args[2].toLowerCase());
                                            ItemStack giveItem = itemManager.createRandItem(name, 1, material, args[2]);
                                            player.getInventory().addItem(giveItem);
                                            player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                                        }else player.sendMessage(ZockerWorldCBV1.prefix + "Diesen Rand gibt es nicht.");
                                    }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.unknownmaterial);
                                }else player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
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
    public static List<String> getRandItems() {
        List<String> alleränder = new ArrayList<>();
        alleränder.add("gold_block");
        alleränder.add("emerald_block");
        alleränder.add("diamond_block");
        alleränder.add("obsidian");
        alleränder.add("end_portal_frame");
        alleränder.add("end_rod");
        alleränder.add("bookshelf");
        alleränder.add("cobweb");
        alleränder.add("beacon");
        alleränder.add("enchanting_table");
        alleränder.add("sea_lantern");
        alleränder.add("glowstone");
        alleränder.add("gilded_blackstone");
        alleränder.add("crying_obsidian");
        alleränder.add("magma_block");
        alleränder.add("jack_o_lantern");
        alleränder.add("campfire");
        return alleränder;
    }
    public static String getName(String insert) {
        if(insert.equalsIgnoreCase("gold_block")) {
            return "§6Gold§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("emerald_block")) {
            return "§aSmaragd§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("diamond_block")) {
            return "§bDiamant§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("obsidian")) {
            return "§5Obsidian§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("end_portal_frame")) {
            return "§dEndportalrahmen§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("end_rod")) {
            return "§dEndrod§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("bookshelf")) {
            return "§3Bücherregal§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("cobweb")) {
            return "§fSpinnennetz§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("beacon")) {
            return "§bBeacon§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("enchanting_table")) {
            return "§dEnchanter§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("sea_lantern")) {
            return "§3Seelaterne§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("glowstone")) {
            return "§6Glowstone§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("gilded_blackstone")) {
            return "§cSpecial §6Gilded Blackstone§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("crying_obsidian")) {
            return "§cSpecial §5Crying Obsidian§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("magma_block")) {
            return "§cSpecial §6Magma block§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("jack_o_lantern")) {
            return "§cSeason §6Jack O Lantern§7-§9Rand";
        }
        if(insert.equalsIgnoreCase("campfire")) {
            return "§cSeason §6Campfire§7-§9Rand";
        }
        return "§7Standard";
    }
}
