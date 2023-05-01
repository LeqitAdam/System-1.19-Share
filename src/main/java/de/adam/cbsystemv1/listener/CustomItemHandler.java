package de.adam.cbsystemv1.listener;

import de.adam.cbsystemv1.commands.CustomItemCommand;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.globalsystemv1.methods.PermsManager;
import de.adam.globalsystemv1.utils.AdvancedItemStack;
import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.globalsystemv1.utils.Tuple;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CustomItemHandler implements Listener {
    public static String keyforrand = "isranditem";
    public static String keyforrank = "isrankwon";
    public static String keyforchest = "chestkey";
    @EventHandler
    public void onRedeemStuff(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
        || event.getAction().equals(Action.LEFT_CLICK_AIR) ||event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if(item != null) {
                //Zocker Rang add
                if(AdvancedItemStack.getNBTTag(item, keyforrank) != null) {
                    if(AdvancedItemStack.getNBTTag(item, keyforrank).equals("§bidentify: §agewonnen in der §dZockerKiste")) {
                        event.setCancelled(true);
                        if(PermsManager.getSmallestSortID(player) > PermsManager.getGroup("Zocker").getSortId()) {
                            addZockerRang(player, item);
                        }else if(player.hasPermission(Permissions.bypassrankaddblock)) {
                            addZockerRang(player, item);
                        }else {
                            player.sendMessage(ZockerWorldCBV1.prefix + Messages.hasrankalready);
                        }
                    }
                }
                //Rand Item perms
                if(AdvancedItemStack.getNBTTag(item, keyforrand) != null) {
                    if(CustomItemCommand.getRandItems().contains(AdvancedItemStack.getNBTTag(item, keyforrand))) {
                        event.setCancelled(true);
                        // Define the map of NBT tag values to materials and permissions
                        Map<String, Tuple<Material, String>> nbtMap = new HashMap<>();
                        nbtMap.put("gold_block", new Tuple<>(Material.GOLD_BLOCK, Permissions.randcommandgold));
                        nbtMap.put("emerald_block", new Tuple<>(Material.EMERALD_BLOCK, Permissions.randcommandsmaragd));
                        nbtMap.put("diamond_block", new Tuple<>(Material.DIAMOND_BLOCK, Permissions.randcommanddia));
                        nbtMap.put("obsidian", new Tuple<>(Material.OBSIDIAN, Permissions.randcommandobi));
                        nbtMap.put("end_portal_frame", new Tuple<>(Material.END_PORTAL_FRAME, Permissions.randcommandendportalframe));
                        nbtMap.put("end_rod", new Tuple<>(Material.END_ROD, Permissions.randcommandendstab));
                        nbtMap.put("bookshelf", new Tuple<>(Material.BOOKSHELF, Permissions.randcommandbookshelf));
                        nbtMap.put("cobweb", new Tuple<>(Material.COBWEB, Permissions.randcommandcobweb));
                        nbtMap.put("beacon", new Tuple<>(Material.BEACON, Permissions.randcommandbeacon));
                        nbtMap.put("enchanting_table", new Tuple<>(Material.ENCHANTING_TABLE, Permissions.randcommandenchanter));
                        nbtMap.put("sea_lantern", new Tuple<>(Material.SEA_LANTERN, Permissions.randcommandsealaterne));
                        nbtMap.put("glowstone", new Tuple<>(Material.GLOWSTONE, Permissions.randcommandglowstone));
                        nbtMap.put("gilded_blackstone", new Tuple<>(Material.GILDED_BLACKSTONE, Permissions.randcommandgildedblackstone));
                        nbtMap.put("crying_obsidian", new Tuple<>(Material.CRYING_OBSIDIAN, Permissions.randcommandcryingobi));
                        nbtMap.put("magma_block", new Tuple<>(Material.MAGMA_BLOCK, Permissions.randcommandmagmablock));
                        nbtMap.put("jack_o_lantern", new Tuple<>(Material.JACK_O_LANTERN, Permissions.randcommandjackolantern));
                        nbtMap.put("campfire", new Tuple<>(Material.CAMPFIRE, Permissions.randcommandcampfire));
                        // Loop through the NBT tag values and add permissions for the corresponding material
                        String tagValue = AdvancedItemStack.getNBTTag(item, keyforrand);
                        if (nbtMap.containsKey(tagValue)) {
                            Tuple<Material, String> materialAndPerm = nbtMap.get(tagValue);
                            addRandPerm(player, item, materialAndPerm.getFirst().name().toLowerCase(), materialAndPerm.getSecond());
                        }

                    }
                }
                //Chest Item non-placeable
                if(AdvancedItemStack.getNBTTag(item, keyforchest) != null) {
                    if(AdvancedItemStack.getNBTTag(item, keyforchest).equals("ZockerChest Key")) {
                        event.setCancelled(true);
                    }
                    if(AdvancedItemStack.getNBTTag(item, keyforchest).equals("GamerChest Key")) {
                        event.setCancelled(true);
                    }
                    if(AdvancedItemStack.getNBTTag(item, keyforchest).equals("SupremeChest Key")) {
                        event.setCancelled(true);
                    }
                    if(AdvancedItemStack.getNBTTag(item, keyforchest).equals("PremiumChest Key")) {
                        event.setCancelled(true);
                    }
                    if(AdvancedItemStack.getNBTTag(item, keyforchest).equals("VoteChest Key")) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
    private void addZockerRang(Player player, ItemStack item) {
        player.sendMessage(ZockerWorldCBV1.prefix + Messages.zockerrankadded);
        player.getInventory().removeItem(item);
        PermsManager.addGroup(player, "Zocker");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }
    private void addRandPerm(Player player, ItemStack item, String block, String permission) {
        if(PermsManager.hasUserPerm(player, permission) && !PermsManager.hasUserPerm(player, Permissions.randcommandbypass)) {
            String rand = CustomItemCommand.getName(block);
            player.sendMessage(ZockerWorldCBV1.prefix + "§7Du hast die §bPermission §7für den " + rand + " §abereits.");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            return;
        }
        String rand = CustomItemCommand.getName(block);
        player.sendMessage(ZockerWorldCBV1.prefix + "§7Du hast die §bPermission §7für den " + rand + " §aerhalten.");
        player.getInventory().removeItem(item);
        PermsManager.addPermission(player,  permission);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }
}
