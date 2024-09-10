package de.adam.cbsystemv1.shop.adminshop.methods;

import de.adam.globalsystemv1.methods.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvManager {
    public static String invname = "§cAdminshop";
    public Inventory getAdminshopInv() {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, invname);
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        InventoryManager.fill(inventory, filler);

        ItemManager items = new ItemManager();
    //1st row
        ItemStack endportal = items.getEndportal();
        ItemStack dragonhead = items.getDragonhead();
        ItemStack dragonegg = items.getDragonegg();
        ItemStack elytra = items.getElytra();
    //2nd row
        ItemStack spawner = items.getSpawner();
        ItemStack beacon = items.getBeacon();
        ItemStack enderchest = items.getEnderchest();
        ItemStack endercrystal = items.getEndcrytal();
    //3rd row
        ItemStack nethritesword = items.getNetheritesword();
        ItemStack netheritepickaxe = items.getNetheritepickaxe();
        ItemStack netheriteaxe = items.getNetheriteaxe();
        ItemStack trident = items.getTrident();
    //4th row
        ItemStack pigspawnegg = items.getPigspawnegg();
        ItemStack blazespawnegg = items.getBlazespawnegg();
        ItemStack endermanspawnegg = items.getEndermanspawnegg();
        ItemStack witherskeletonspawnegg = items.getWitherskeletonspawnnegg();

        //1st row
        inventory.setItem(10, endportal);
        inventory.setItem(19, dragonhead);
        inventory.setItem(28, dragonegg);
        inventory.setItem(37, elytra);
        //2nd row
        inventory.setItem(12, spawner);
        inventory.setItem(21, beacon);
        inventory.setItem(30, enderchest);
        inventory.setItem(39, endercrystal);
        //3rd row
        inventory.setItem(14, nethritesword);
        inventory.setItem(23, netheritepickaxe);
        inventory.setItem(32, netheriteaxe);
        inventory.setItem(41, trident);
        //4th row
        inventory.setItem(16, pigspawnegg);
        inventory.setItem(25, blazespawnegg);
        inventory.setItem(34, endermanspawnegg);
        inventory.setItem(43, witherskeletonspawnegg);
        return inventory;
    }
}
