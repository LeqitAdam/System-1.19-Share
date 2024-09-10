package de.adam.cbsystemv1.shop.adminshop.villager;

import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.shop.adminshop.methods.InvManager;
import de.adam.globalsystemv1.main.GlobalSystemSpigot;
import de.adam.globalsystemv1.utils.ReformatText;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;

public class VillagerHandler implements Listener {
    @EventHandler
    public void handleShopInteract(PlayerInteractEntityEvent e) {
        String villagername = VillagerHandler.getAdminshopname();
        if(!(e.getRightClicked() instanceof Villager)) return;
        Villager shop = (Villager) e.getRightClicked();
        villagername = ChatColor.translateAlternateColorCodes('&', villagername);

        if(shop.getCustomName().equals(villagername)) {
            e.setCancelled(true);
            Player player = e.getPlayer();
            InvManager invManager = new InvManager();
            Inventory inv = invManager.getAdminshopInv();
            player.openInventory(inv);
        }
    }
    @EventHandler
    public void handleShopDamage(EntityDamageByEntityEvent e) {
        String villagername = VillagerHandler.getAdminshopname();
        if(!(e.getEntity() instanceof Villager)) return;
        Villager shop = (Villager) e.getEntity();
        villagername = ChatColor.translateAlternateColorCodes('&', villagername);
        if(!shop.getCustomName().equals(villagername)) return;

        e.setCancelled(true);
        if(!(e.getDamager() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        if(p.hasPermission(Permissions.killvillager)) {
            if(p.getGameMode().equals(GameMode.CREATIVE)) {
                if(p.getItemInHand().getType() == Material.LAVA_BUCKET) {
                    shop.setHealth(0);
                    p.sendMessage("§aDu hast den Shop entfernt");
                }
            }
        }
    }
    public static String getAdminshopname() {
        File config = new File(GlobalSystemSpigot.pluginpath, "config.yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        String villagername = conf.getString("AdminShop");
        return villagername;
    }
}
