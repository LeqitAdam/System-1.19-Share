package de.adam.cbsystemv1.methods;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.globalsystemv1.listener.PlayerHandler;
import de.adam.globalsystemv1.utils.AdvancedItemStack;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PackManager {
    public static void giveEquip(Player player, ItemStack item, int stufe) {
        try {
            switch (stufe) {
                case 1:
                    addEquipI(player, item);
                default:
                    player.sendMessage("Falsche Equip stufe");
            }
        }catch (NumberFormatException e) {
            player.sendMessage("Nutze eine Zahl im Code für das Equip");
        }
    }
    public static void giveArmor(Player player, ItemStack item, int stufe) {
       addArmor(player, item, stufe);
    }

    private static void addEquipI(Player player, ItemStack item) {
        ItemStack schwert = new ItemStack(Material.IRON_SWORD);
        ItemStack bogen = new ItemStack(Material.BOW);
        ItemStack armbrust = new ItemStack(Material.CROSSBOW);
        ItemStack axt = new ItemStack(Material.IRON_AXE);
        ItemStack spitzhacke = new ItemStack(Material.IRON_PICKAXE);
        ItemStack schaufel = new ItemStack(Material.IRON_SHOVEL);
        ItemStack hacke = new ItemStack(Material.IRON_HOE);
        ItemStack schild = new ItemStack(Material.SHIELD);
        ItemStack pfeile = new ItemStack(Material.ARROW, 64);

        if(PlayerHandler.itemsfitinPlayersInv(player, 9)) {
            Inventory inv = player.getInventory();
            inv.addItem(schwert);
            inv.addItem(bogen);
            inv.addItem(armbrust);
            inv.addItem(axt);
            inv.addItem(spitzhacke);
            inv.addItem(schaufel);
            inv.addItem(hacke);
            inv.addItem(schild);
            inv.addItem(pfeile);
            inv.removeItem(item);
            player.sendMessage(ZockerWorldCBV1.prefix + "§aDu hast das §9Ausrüstungspack I §aerhalten.");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }else {
            player.sendMessage(ZockerWorldCBV1.prefix + Messages.notenoughinvspace);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
    private static void addArmor(Player player, ItemStack item, int stufe) {
        AdvancedItemStack helm = null;
        AdvancedItemStack chestplate = null;
        AdvancedItemStack hose = null;
        AdvancedItemStack schuhe = null;
        String stufes = null;
        if(stufe == 1) {
            helm = new AdvancedItemStack(Material.IRON_HELMET);
            helm.addEnchantment(helm, Enchantment.PROTECTION_ENVIRONMENTAL, 3);

            chestplate = new AdvancedItemStack(Material.IRON_CHESTPLATE);
            chestplate.addEnchantment(chestplate, Enchantment.PROTECTION_ENVIRONMENTAL, 3);

            hose = new AdvancedItemStack(Material.IRON_LEGGINGS);
            hose.addEnchantment(hose, Enchantment.PROTECTION_ENVIRONMENTAL, 3);

            schuhe = new AdvancedItemStack(Material.IRON_BOOTS);
            schuhe.addEnchantment(schuhe, Enchantment.PROTECTION_ENVIRONMENTAL, 3);
            
            stufes = "I";
        }
        if(stufe == 2) {
            helm = new AdvancedItemStack(Material.DIAMOND_HELMET);
            helm.addEnchantment(helm, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

            chestplate = new AdvancedItemStack(Material.DIAMOND_CHESTPLATE);
            chestplate.addEnchantment(chestplate, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

            hose = new AdvancedItemStack(Material.DIAMOND_LEGGINGS);
            hose.addEnchantment(hose, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

            schuhe = new AdvancedItemStack(Material.DIAMOND_BOOTS);
            schuhe.addEnchantment(schuhe, Enchantment.PROTECTION_ENVIRONMENTAL, 2);

            stufes = "II";
        }
        if(stufe == 3) {
            helm = new AdvancedItemStack(Material.NETHERITE_HELMET);
            chestplate = new AdvancedItemStack(Material.NETHERITE_CHESTPLATE);
            hose = new AdvancedItemStack(Material.NETHERITE_LEGGINGS);
            schuhe = new AdvancedItemStack(Material.NETHERITE_BOOTS);

            stufes = "III";
        }

        if(PlayerHandler.itemsfitinPlayersInv(player, 4)) {
            Inventory inv = player.getInventory();
            inv.addItem(helm);
            inv.addItem(chestplate);
            inv.addItem(hose);
            inv.addItem(schuhe);
            inv.removeItem(item);

            player.sendMessage(ZockerWorldCBV1.prefix + "§aDu hast das §9Rüstungspack " + stufes + " §aerhalten.");
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }else {
            player.sendMessage(ZockerWorldCBV1.prefix + Messages.notenoughinvspace);
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
        }
    }
}
