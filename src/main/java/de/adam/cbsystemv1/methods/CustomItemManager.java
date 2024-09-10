package de.adam.cbsystemv1.methods;

import com.google.common.collect.Lists;
import de.adam.cbsystemv1.listener.CustomItemHandler;
import de.adam.globalsystemv1.utils.AdvancedItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomItemManager {
    public ItemStack createZockerRangItem(String name, int amount, Material material) {
        AdvancedItemStack createdItem = new AdvancedItemStack(material, amount);
        ItemMeta itemMeta = createdItem.getItemMeta();
        String itemname = ChatColor.translateAlternateColorCodes('&', name);
        itemMeta.setDisplayName(itemname);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = (List<String>) itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add("§dZocker§5Rang §7- §aItem");
        lore.add("§eRechtsclick §7zum einlösen.");
        itemMeta.setLore(lore);
        createdItem.setItemMeta(itemMeta);
        AdvancedItemStack.setNBTTag(createdItem, CustomItemHandler.keyforrank, "§bidentify: §agewonnen in der §dZockerKiste");
        createdItem.addEnchantment(createdItem, Enchantment.LOOT_BONUS_BLOCKS, 21);
        return createdItem;
    }
    public ItemStack createRandItem(String name, int amount, Material material, String value) {
        AdvancedItemStack createdItem = new AdvancedItemStack(material, amount);
        ItemMeta itemMeta = createdItem.getItemMeta();
        String itemname = ChatColor.translateAlternateColorCodes('&', name);
        itemMeta.setDisplayName(itemname);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = (List<String>) itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(name + " §aItem");
        lore.add("§eRechtsclick §7zum einlösen.");
        itemMeta.setLore(lore);
        createdItem.setItemMeta(itemMeta);
        AdvancedItemStack.setNBTTag(createdItem, CustomItemHandler.keyforrand, value);
        createdItem.addEnchantment(createdItem, Enchantment.LOOT_BONUS_BLOCKS, 21);
        return createdItem;
    }
    public ItemStack createChestItem(String name, int amount, Material material, String value) {
        AdvancedItemStack createdItem = new AdvancedItemStack(material, amount);
        ItemMeta itemMeta = createdItem.getItemMeta();
        String itemname = ChatColor.translateAlternateColorCodes('&', name);
        itemMeta.setDisplayName(itemname);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§b§m--------------------------------");
        lore.add("§bSchlüssel für die " + name + "§b.");
        lore.add("§aRechtsclick §bauf die Kiste zum öffnen.");
        itemMeta.setLore(lore);
        createdItem.setItemMeta(itemMeta);
        AdvancedItemStack.setNBTTag(createdItem, CustomItemHandler.keyforchest, value);
        createdItem.addEnchantment(createdItem, Enchantment.DURABILITY, 10);
        return createdItem;
    }

    //Equip Packs
    public ItemStack createEquipI(String name, int amount, Material material, String value) {
        AdvancedItemStack createdItem = new AdvancedItemStack(material, amount);
        ItemMeta itemMeta = createdItem.getItemMeta();
        String itemname = ChatColor.translateAlternateColorCodes('&', name);
        itemMeta.setDisplayName(itemname);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§b§m--------------------------------");
        lore.add("§aIm Pack enthalten:");
        lore.add("§b1x Eisenschwert");
        lore.add("§b1x Bogen");
        lore.add("§b1x Armbrust");
        lore.add("§b1x Eisenspitzhacke");
        lore.add("§b1x Eisenaxt");
        lore.add("§b1x Eisenschaufel");
        lore.add("§b1x Eisenhacke");
        lore.add("§b1x Schild");
        lore.add("§b64x Pfeile");
        lore.add("§aRechtsclick §bum das Paket zu erhalten.");
        itemMeta.setLore(lore);
        createdItem.setItemMeta(itemMeta);
        AdvancedItemStack.setNBTTag(createdItem, CustomItemHandler.keyforequip, value);
        createdItem.addEnchantment(createdItem, Enchantment.DURABILITY, 10);
        return createdItem;
    }
    //Armor Packs
    public ItemStack createArmor(String name, int amount, Material material, String value) {
        AdvancedItemStack createdItem = new AdvancedItemStack(material, amount);
        ItemMeta itemMeta = createdItem.getItemMeta();
        String itemname = ChatColor.translateAlternateColorCodes('&', name);
        itemMeta.setDisplayName(itemname);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§b§m--------------------------------");
        lore.add("§aIm Pack enthalten:");
        if(material.equals(Material.IRON_CHESTPLATE)) {
            lore.add("§b1x Eisenhelm §7(Schutz 3)");
            lore.add("§b1x Eisenbrustpanzer §7(Schutz 3)");
            lore.add("§b1x Eisenhose §7(Schutz 3)");
            lore.add("§b1x Eisenschuhe §7(Schutz 3)");
        }
        if(material.equals(Material.DIAMOND_CHESTPLATE)) {
            lore.add("§b1x Diamanthelm §7(Schutz 2)");
            lore.add("§b1x Diamantbrustpanzer §7(Schutz 2)");
            lore.add("§b1x Diamanthose §7(Schutz 2)");
            lore.add("§b1x Diamantschuhe §7(Schutz 2)");
        }
        if(material.equals(Material.NETHERITE_CHESTPLATE)) {
            lore.add("§b1x Netheritehelm");
            lore.add("§b1x Neheritebrustpanzer");
            lore.add("§b1x Netheritehose");
            lore.add("§b1x Netheriteschuhe");
        }
        lore.add("§aRechtsclick §bum das Paket zu erhalten.");
        itemMeta.setLore(lore);
        createdItem.setItemMeta(itemMeta);
        AdvancedItemStack.setNBTTag(createdItem, CustomItemHandler.keyforarmor, value);
        createdItem.addEnchantment(createdItem, Enchantment.DURABILITY, 10);
        return createdItem;
    }
}
