package de.adam.cbsystemv1.shop.adminshop.listener;

import de.adam.cbsystemv1.listener.InventoryClickHandler;
import de.adam.cbsystemv1.shop.files.Names;
import de.adam.cbsystemv1.shop.files.Prices;
import de.adam.cbsystemv1.shop.methods.BuyManager;
import de.adam.cbsystemv1.shop.adminshop.methods.InvManager;
import de.adam.cbsystemv1.shop.adminshop.methods.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler implements Listener {
    @EventHandler
    public void onAdminshopInv(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals(InvManager.invname)) {
            if (event.getClickedInventory() == null) {
                return;
            }
            if(event.getCurrentItem() == null) {
                return;
            }
            InventoryClickHandler.getNoClick().add(player.getUniqueId());
            ItemManager items = new ItemManager();
            BuyManager shop = new BuyManager();
            //1st row
            if(event.getCurrentItem().equals(items.getEndportal())) {
                shop.buyItem(player, Material.END_PORTAL_FRAME, Prices.endportalpreis, Names.endportalname);
            }
            if(event.getCurrentItem().equals(items.getDragonhead())) {
                shop.buyItem(player, Material.DRAGON_HEAD, Prices.dragonheadpreis, Names.dragonheadname);
            }
            if(event.getCurrentItem().equals(items.getDragonegg())) {
                shop.buyItem(player, Material.DRAGON_EGG, Prices.dragoneggpreis, Names.dragoneggname);
            }
            if(event.getCurrentItem().equals(items.getElytra())) {
                shop.buyItem(player, Material.ELYTRA, Prices.elytrapreis, Names.elytraname);
            }
            //2nd row
            if(event.getCurrentItem().equals(items.getSpawner())) {
                shop.buyItem(player, Material.SPAWNER, Prices.spawnerpreis, Names.spawnername);
            }
            if(event.getCurrentItem().equals(items.getBeacon())) {
                shop.buyItem(player, Material.BEACON, Prices.beaconpreis, Names.beaconname);
            }
            if(event.getCurrentItem().equals(items.getEnderchest())) {
                shop.buyItem(player, Material.ENDER_CHEST, Prices.enderchestpreis, Names.enderchestname);
            }
            if(event.getCurrentItem().equals(items.getEndcrytal())) {
                shop.buyItem(player, Material.END_CRYSTAL, Prices.endercrytalpreis, Names.endercrytalname);
            }
            //3rd row
            if(event.getCurrentItem().getType().equals(Material.NETHERITE_SWORD)) {
                ItemStack item = items.getNetheritesword();
                shop.buyEnchantedItem(player, item, Prices.netheritesword, Names.netheriteswordname);
            }
            if(event.getCurrentItem().getType().equals(Material.NETHERITE_PICKAXE)) {
                ItemStack item = items.getNetheritepickaxe();
                shop.buyEnchantedItem(player, item, Prices.netheritepickaxe, Names.netheritepickaxename);
            }
            if(event.getCurrentItem().getType().equals(Material.NETHERITE_AXE)) {
                ItemStack item = items.getNetheriteaxe();
                shop.buyEnchantedItem(player, item, Prices.netheriteaxe, Names.netheriteaxename);
            }
            if(event.getCurrentItem().equals(items.getTrident())) {
                shop.buyItem(player, Material.TRIDENT, Prices.tridentpreis, Names.tridentname);
            }
            //4th row
            if(event.getCurrentItem().equals(items.getPigspawnegg())) {
                shop.buyItem(player, Material.PIG_SPAWN_EGG, Prices.pigspawneggpreis, Names.pigspawneggname);
            }
            if(event.getCurrentItem().equals(items.getBlazespawnegg())) {
                shop.buyItem(player, Material.BLAZE_SPAWN_EGG, Prices.lohespawneggpreis, Names.lohespawneggname);
            }
            if(event.getCurrentItem().equals(items.getEndermanspawnegg())) {
                shop.buyItem(player, Material.ENDERMAN_SPAWN_EGG, Prices.endermanspawneggpreis, Names.endermanspawneggname);
            }
            if(event.getCurrentItem().equals(items.getWitherskeletonspawnnegg())) {
                shop.buyItem(player, Material.WITHER_SKELETON_SPAWN_EGG, Prices.witherskelettspawneggpreis, Names.witherskelettspawneggname);
            }
        }
    }
}
