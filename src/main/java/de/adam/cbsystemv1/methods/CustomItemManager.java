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
            lore = (List<String>) itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add("§7Schlüssel für die " + name + "§7.");
        itemMeta.setLore(lore);
        createdItem.setItemMeta(itemMeta);
        AdvancedItemStack.setNBTTag(createdItem, CustomItemHandler.keyforchest, value);
        createdItem.addEnchantment(createdItem, Enchantment.LOOT_BONUS_BLOCKS, 21);
        return createdItem;
    }
}
