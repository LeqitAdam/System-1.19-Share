package de.adam.cbsystemv1.commands;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.CustomItemManager;
import de.adam.cbsystemv1.methods.ItemManager;
import de.adam.globalsystemv1.utils.ReformatText;
import net.md_5.bungee.api.ChatColor;
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
                            }else if(args[1].equalsIgnoreCase("chestitem") || args[1].equalsIgnoreCase("chest")) {
                                if(player.hasPermission(Permissions.createchestitem)) {
                                    if(args[2].equalsIgnoreCase("zockerchest") || args[2].equalsIgnoreCase("zocker")) {
                                        Material material = Material.PURPLE_SHULKER_BOX;
                                        String name = "§x§6§1§0§0§b§4§lZ§x§6§e§0§0§b§b§lo§x§7§a§0§0§c§2§lc§x§8§7§0§0§c§9§lk§x§9§4§0§0§d§0§le§x§a§1§0§0§d§7§lr§x§a§d§0§0§d§e§lK§x§b§a§0§0§e§5§li§x§c§7§0§0§e§c§ls§x§d§3§0§0§f§3§lt§x§e§0§0§0§f§a§le";
                                        ItemStack giveItem = itemManager.createChestItem(name, 1, material, "ZockerChest Key");
                                        player.getInventory().addItem(giveItem);
                                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                                    }else if(args[2].equalsIgnoreCase("gamerchest") ||args[2].equalsIgnoreCase("gamer")) {
                                        Material material = Material.ENDER_CHEST;
                                        String name = "§x§6§1§0§0§b§4§lZ§x§6§e§0§0§b§b§lo§x§7§a§0§0§c§2§lc§x§8§7§0§0§c§9§lk§x§9§4§0§0§d§0§le§x§a§1§0§0§d§7§lr§x§a§d§0§0§d§e§lK§x§b§a§0§0§e§5§li§x§c§7§0§0§e§c§ls§x§d§3§0§0§f§3§lt§x§e§0§0§0§f§a§le";
                                        ItemStack giveItem = itemManager.createChestItem(name, 1, material, "GamerChest Key");
                                        player.getInventory().addItem(giveItem);
                                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                                    }else if(args[2].equalsIgnoreCase("supremechest") ||args[2].equalsIgnoreCase("supreme")) {
                                        String name = "§x§6§1§0§0§b§4§lZ§x§6§e§0§0§b§b§lo§x§7§a§0§0§c§2§lc§x§8§7§0§0§c§9§lk§x§9§4§0§0§d§0§le§x§a§1§0§0§d§7§lr§x§a§d§0§0§d§e§lK§x§b§a§0§0§e§5§li§x§c§7§0§0§e§c§ls§x§d§3§0§0§f§3§lt§x§e§0§0§0§f§a§le";
                                        Material material = Material.ENDER_CHEST;
                                        ItemStack giveItem = itemManager.createChestItem(name, 1, material, "SupremeChest Key");
                                        player.getInventory().addItem(giveItem);
                                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                                    }else if(args[2].equalsIgnoreCase("premiumchest") ||args[2].equalsIgnoreCase("premium")) {
                                        String name = "§x§6§1§0§0§b§4§lZ§x§6§e§0§0§b§b§lo§x§7§a§0§0§c§2§lc§x§8§7§0§0§c§9§lk§x§9§4§0§0§d§0§le§x§a§1§0§0§d§7§lr§x§a§d§0§0§d§e§lK§x§b§a§0§0§e§5§li§x§c§7§0§0§e§c§ls§x§d§3§0§0§f§3§lt§x§e§0§0§0§f§a§le";
                                        Material material = Material.CHEST;
                                        ItemStack giveItem = itemManager.createChestItem(name, 1, material, "PremiumChest Key");
                                        player.getInventory().addItem(giveItem);
                                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                                    }else if(args[2].equalsIgnoreCase("votechest") ||args[2].equalsIgnoreCase("vote")) {
                                        String name = "§x§6§1§0§0§b§4§lZ§x§6§e§0§0§b§b§lo§x§7§a§0§0§c§2§lc§x§8§7§0§0§c§9§lk§x§9§4§0§0§d§0§le§x§a§1§0§0§d§7§lr§x§a§d§0§0§d§e§lK§x§b§a§0§0§e§5§li§x§c§7§0§0§e§c§ls§x§d§3§0§0§f§3§lt§x§e§0§0§0§f§a§le";
                                        Material material = Material.CHEST;
                                        ItemStack giveItem = itemManager.createChestItem(name, 1, material, "VoteChest Key");
                                        player.getInventory().addItem(giveItem);
                                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.customitemcreated);
                                    }
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
