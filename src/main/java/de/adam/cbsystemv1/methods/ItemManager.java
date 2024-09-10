package de.adam.cbsystemv1.methods;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ItemManager {
    public static Material getMaterialFromString(String materialName) {
        // Replace underscores with spaces and capitalize first letter of each word
        String formattedName = Arrays.stream(materialName.split("_"))
                .map(String::toLowerCase)
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));

        // Get the Material enum value
        try {
            return Material.valueOf(formattedName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static Material getMaterialFromStringAlternative(String materialName) {
        Material material = Material.matchMaterial(materialName);
        return material;
    }

    //Warp Items
    public ItemStack getSpawnWarpItem() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§aSpawn§7-§bWarp");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getCBWarpItem() {
        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§9CityBuild§7-§bWarp");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getFarmweltWarpItem() {
        ItemStack item = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§2Farmwelt§7-§bWarp");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getNetherWarpItem() {
        ItemStack item = new ItemStack(Material.NETHER_BRICK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§4Nether§7-§9Warp");
        item.setItemMeta(itemMeta);
        return item;
    }
    //Rand Items
    public ItemStack getRandDefault() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§7Standard §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandGold() {
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§6Gold §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandSmaragd() {
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§aSmaragd §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandDia() {
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§bDiamant §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandObsidian() {
        ItemStack item = new ItemStack(Material.OBSIDIAN);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§5Obsidian §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandEndportalframe() {
        ItemStack item = new ItemStack(Material.END_PORTAL_FRAME);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§dEndportalrahmen §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandEnderstab() {
        ItemStack item = new ItemStack(Material.END_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§dEnderstab §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandBookshelf() {
        ItemStack item = new ItemStack(Material.BOOKSHELF);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§3Bücherregal §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandCobweb() {
        ItemStack item = new ItemStack(Material.COBWEB);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§fSpinnennetz §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandBeacon() {
        ItemStack item = new ItemStack(Material.BEACON);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§bBeacon §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandEnchanter() {
        ItemStack item = new ItemStack(Material.ENCHANTING_TABLE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§dEnchanter §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandLantern() {
        ItemStack item = new ItemStack(Material.SEA_LANTERN);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§3Seelaterne §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandGlowstone() {
        ItemStack item = new ItemStack(Material.GLOWSTONE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§6Glowstone §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandGildedBlackstone() {
        ItemStack item = new ItemStack(Material.GILDED_BLACKSTONE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§cSpecial §6Gilded Blackstone §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandCryingObi() {
        ItemStack item = new ItemStack(Material.CRYING_OBSIDIAN);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§cSpecial §5Crying Obsidian §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandMagmablock() {
        ItemStack item = new ItemStack(Material.MAGMA_BLOCK);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§cSpecial §6Magma block §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandJackOLantern() {
        ItemStack item = new ItemStack(Material.JACK_O_LANTERN);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§cSeason §6Kürbislaterne §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
    public ItemStack getRandCampfire() {
        ItemStack item = new ItemStack(Material.CAMPFIRE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName("§cSeason §6Campfire §9Rand");
        item.setItemMeta(itemMeta);
        return item;
    }
}
