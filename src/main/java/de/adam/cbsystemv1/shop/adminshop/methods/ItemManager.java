package de.adam.cbsystemv1.shop.adminshop.methods;

import com.google.common.collect.Lists;
import de.adam.cbsystemv1.shop.files.Names;
import de.adam.cbsystemv1.shop.files.Prices;
import de.adam.globalsystemv1.utils.AdvancedItemStack;
import de.adam.globalsystemv1.utils.ReformatNumber;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemManager {
    //1st row
    public ItemStack getEndportal() {
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.endportalpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.endportalname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getDragonhead() {
        ItemStack item = new ItemStack(Material.DRAGON_HEAD, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.dragonheadpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.dragonheadname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getDragonegg() {
        ItemStack item = new ItemStack(Material.DRAGON_EGG, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.dragoneggpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.dragoneggname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getElytra() {
        ItemStack item = new ItemStack(Material.ELYTRA, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.elytrapreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.elytraname);
        item.setItemMeta(itemMeta);
        return item;
    }
    //2nd row
    public ItemStack getSpawner() {
        ItemStack item = new ItemStack(Material.SPAWNER, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.spawnerpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.spawnername);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getBeacon() {
        ItemStack item = new ItemStack(Material.BEACON, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.beaconpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.beaconname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getEnderchest() {
        ItemStack item = new ItemStack(Material.ENDER_CHEST, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.enderchestpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.enderchestname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getEndcrytal() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.endercrytalpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.endercrytalname);
        item.setItemMeta(itemMeta);
        return item;
    }
    //3rd row
    public ItemStack getNetheritesword() {
        AdvancedItemStack item = new AdvancedItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.netheritesword);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.netheriteswordname);
        item.setItemMeta(itemMeta);
        item.addEnchantment(item, Enchantment.DAMAGE_ALL, 50);
        item.addEnchantment(item, Enchantment.DURABILITY, 10);
        item.addEnchantment(item, Enchantment.MENDING, 10);
        return item;
    }
    public ItemStack getNetheritepickaxe() {
        AdvancedItemStack item = new AdvancedItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.netheritepickaxe);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.netheritepickaxename);
        item.setItemMeta(itemMeta);
        item.addEnchantment(item, Enchantment.DIG_SPEED, 10);
        item.addEnchantment(item, Enchantment.DURABILITY, 10);
        item.addEnchantment(item, Enchantment.MENDING, 10);
        item.addEnchantment(item, Enchantment.LOOT_BONUS_BLOCKS, 2);
        return item;
    }
    public ItemStack getNetheriteaxe() {
        AdvancedItemStack item = new AdvancedItemStack(Material.NETHERITE_AXE, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.netheriteaxe);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.netheriteaxename);
        item.setItemMeta(itemMeta);
        item.addEnchantment(item, Enchantment.DIG_SPEED, 10);
        item.addEnchantment(item, Enchantment.DURABILITY, 10);
        item.addEnchantment(item, Enchantment.MENDING, 10);
        return item;
    }
    public ItemStack getTrident() {
        ItemStack item = new ItemStack(Material.TRIDENT, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.tridentpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.tridentname);
        item.setItemMeta(itemMeta);
        return item;
    }
    //4th row
    public ItemStack getPigspawnegg() {
        ItemStack item = new ItemStack(Material.PIG_SPAWN_EGG, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.pigspawneggpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.pigspawneggname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getBlazespawnegg() {
        ItemStack item = new ItemStack(Material.BLAZE_SPAWN_EGG, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.lohespawneggpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.lohespawneggname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getEndermanspawnegg() {
        ItemStack item = new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.endermanspawneggpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.endermanspawneggname);
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getWitherskeletonspawnnegg() {
        ItemStack item = new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG, 1);
        ItemMeta itemMeta = item.getItemMeta();
        String preis = ReformatNumber.formatCurrency(Prices.witherskelettspawneggpreis);
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add(" ");
        lore.add("§7Kaufpreis: §b" + preis);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(Names.witherskelettspawneggname);
        item.setItemMeta(itemMeta);
        return item;
    }
}
