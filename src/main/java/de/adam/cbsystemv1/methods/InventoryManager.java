package de.adam.cbsystemv1.methods;

import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.shop.files.Names;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {
    public Inventory createInv(String invname, int size) {
        Inventory inventory = Bukkit.createInventory(null, size, invname);
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        de.adam.globalsystemv1.methods.InventoryManager.fill(inventory, filler);
        return inventory;
    }
    public Inventory getWarpInv() {
        Inventory inventory = createInv(Names.warpinvname, 3 * 9);
        ItemManager items = new ItemManager();
        ItemStack spawn = items.getSpawnWarpItem();
        ItemStack cb = items.getCBWarpItem();
        ItemStack farmwelt = items.getFarmweltWarpItem();
        ItemStack nether = items.getNetherWarpItem();
        inventory.setItem(13, spawn);
        inventory.setItem(1, cb);
        inventory.setItem(19, farmwelt);
        inventory.setItem(25, nether);
        return inventory;
    }
    public Inventory getRandInv(Player player) {
        Inventory inv = createInv(Names.randinvname, 2 * 9);
        ItemManager items = new ItemManager();
        //1st row
        ItemStack randDefault = items.getRandDefault();
        ItemStack randGold = items.getRandGold();
        ItemStack randSmaragd = items.getRandSmaragd();
        ItemStack randDia = items.getRandDia();
        ItemStack randObi = items.getRandObsidian();
        ItemStack randEndportalframe = items.getRandEndportalframe();
        ItemStack randEnderstab = items.getRandEnderstab();
        ItemStack randBücherregal = items.getRandBookshelf();
        ItemStack randCobweb = items.getRandCobweb();
        //2nd row
        ItemStack randBeacon = items.getRandBeacon();
        ItemStack randEnchanter = items.getRandEnchanter();
        ItemStack randSealaterne = items.getRandLantern();
        ItemStack randGlowstone = items.getRandGlowstone();
        ItemStack randGlidedblackstone = items.getRandGildedBlackstone();
        ItemStack randCryingObi = items.getRandCryingObi();
        ItemStack randMagmablock = items.getRandMagmablock();
        ItemStack randJackolantern = items.getRandJackOLantern();
        ItemStack randCampfire = items.getRandCampfire();

        //1st row
        inv.setItem(0, randDefault);
        if(player.hasPermission(Permissions.randcommandgold)) {
            inv.setItem(1, randGold);
        }
        if(player.hasPermission(Permissions.randcommandsmaragd)) {
            inv.setItem(2, randSmaragd);
        }
        if(player.hasPermission(Permissions.randcommanddia)) {
            inv.setItem(3, randDia);
        }
        if(player.hasPermission(Permissions.randcommandobi)) {
            inv.setItem(4, randObi);
        }
        if(player.hasPermission(Permissions.randcommandendportalframe)) {
            inv.setItem(5, randEndportalframe);
        }
        if(player.hasPermission(Permissions.randcommandendstab)) {
            inv.setItem(6, randEnderstab);
        }
        if(player.hasPermission(Permissions.randcommandbookshelf)) {
            inv.setItem(7, randBücherregal);
        }
        if(player.hasPermission(Permissions.randcommandcobweb)) {
            inv.setItem(8, randCobweb);
        }
        //2nd row
        if(player.hasPermission(Permissions.randcommandbeacon)) {
            inv.setItem(9, randBeacon);
        }
        if(player.hasPermission(Permissions.randcommandenchanter)) {
            inv.setItem(10, randEnchanter);
        }
        if(player.hasPermission(Permissions.randcommandsealaterne)) {
            inv.setItem(11, randSealaterne);
        }
        if(player.hasPermission(Permissions.randcommandglowstone)) {
            inv.setItem(12, randGlowstone);
        }
        if(player.hasPermission(Permissions.randcommandgildedblackstone)) {
            inv.setItem(13, randGlidedblackstone);
        }
        if(player.hasPermission(Permissions.randcommandcryingobi)) {
            inv.setItem(14, randCryingObi);
        }
        if(player.hasPermission(Permissions.randcommandmagmablock)) {
            inv.setItem(15, randMagmablock);
        }
        if(player.hasPermission(Permissions.randcommandjackolantern)) {
            inv.setItem(16, randJackolantern);
        }
        if(player.hasPermission(Permissions.randcommandcampfire)) {
            inv.setItem(17, randCampfire);
        }

        return inv;
    }
}
