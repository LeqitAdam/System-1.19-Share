package de.adam.cbsystemv1.listener;
import com.google.common.collect.Lists;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.shop.files.Names;
import de.adam.globalsystemv1.files.uuid;
import de.adam.globalsystemv1.methods.PermsManager;
import de.adam.globalsystemv1.utils.ReformatText;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.UUID;
public class InventoryClickHandler implements Listener {
    public static List<UUID> getNoClick() {
        return noClick;
    }
    public static List<UUID> noClick = Lists.newArrayList();
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onclick(final InventoryClickEvent event) {
        if(event.getCurrentItem() != null) {
            if (getNoClick().contains(event.getWhoClicked().getUniqueId())) {
                if(!event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
                    event.setCancelled(true);
                }else if(event.getClick().isShiftClick()) {
                    event.setCancelled(true);
                }else if(event.getClick() == ClickType.DOUBLE_CLICK) {
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(final InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (getNoClick().contains(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
        }

        String title = event.getView().getTitle();
        if(title.contains("§5§lEnderChest") ||title.contains("§dEnder Chest")) {
            if(event.getView().getTitle().equals("§5§lEnderChest §7- §5" + player.getName())) {
                return;
            }
            if(player.hasPermission("zockerworld.ec.edit")) {
                OfflinePlayer offtarget = ReformatText.getOffPlayerfromECTitle(title);
                IPermissionUser permsuser  = CloudNetDriver.getInstance().getPermissionManagement().getUser(offtarget.getUniqueId());
                int playersort = PermsManager.getSmallestSortID(player);
                int targetsort = PermsManager.getOffSmallestSortID(offtarget);
                if(playersort <= targetsort && !PermsManager.hasUserPerm(permsuser, "zockerworld.edit.ec.*")) {
                    return;
                }
                if(player.getUniqueId().equals(uuid.leqitadam) || player.hasPermission("zockerworld.ec.edit.*")) {
                    return;
                }
            }
            event.setCancelled(true);
        }
    }
    @EventHandler
    private void checkInvClick(final InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();
        //Warp inv
        if(title.equals(Names.warpinvname)) {
            getNoClick().add(player.getUniqueId());
        }
        //Rand inv
        if(title.equals(Names.randinvname)) {
            getNoClick().add(player.getUniqueId());
        }
        if(title.contains("§5§lEnderChest") ||title.contains("§dEnder Chest")) {
            if(event.getView().getTitle().equals("§5§lEnderChest §7- §5" + player.getName())) {
                return;
            }
            if(player.hasPermission("zockerworld.ec.edit")) {
                OfflinePlayer offtarget = ReformatText.getOffPlayerfromECTitle(title);
                IPermissionUser permsuser  = CloudNetDriver.getInstance().getPermissionManagement().getUser(offtarget.getUniqueId());
                int playersort = PermsManager.getSmallestSortID(player);
                int targetsort = PermsManager.getOffSmallestSortID(offtarget);
                if(playersort <= targetsort  && !PermsManager.hasUserPerm(permsuser, "zockerworld.edit.ec.*")) {
                    return;
                }
                if(player.getUniqueId().equals(uuid.leqitadam) || player.hasPermission("zockerworld.ec.edit.*")) {
                    return;
                }
            }
            Inventory playerInv = player.getOpenInventory().getTopInventory();
            if(event.getClickedInventory().equals(playerInv)) {
                event.setCancelled(true);
            }else if(event.getClick().isShiftClick()) {
                event.setCancelled(true);
            }else if(event.getClick().equals(ClickType.DOUBLE_CLICK)) {
                event.setCancelled(true);
            }else if(event.getClick().equals(ClickType.CONTROL_DROP)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onClose(InventoryCloseEvent event) {
        if (getNoClick().contains(event.getPlayer().getUniqueId())) {
            getNoClick().remove(event.getPlayer().getUniqueId());
        }
    }
    @EventHandler
    public void onBuild(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(Permissions.interact)) {
            PlotPlayer plotPlayer = PlotPlayer.from(player);
            Plot plot = plotPlayer.getCurrentPlot();
            if(plot != null) {
                if(plot.isOwner(player.getUniqueId()) || plot.isAdded(player.getUniqueId())) {
                    return;
                }
                checkPerms(player, event);
            }else if(plotPlayer.getLocation().isPlotArea()) {
                checkPerms(player, event);
            }
        }
    }
    @EventHandler
    public void onEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(Permissions.interact)) {
            PlotPlayer plotPlayer = PlotPlayer.from(player);
            Plot plot = plotPlayer.getCurrentPlot();
            if(plot != null) {
                if(plot.isOwner(player.getUniqueId()) || plot.isAdded(player.getUniqueId())) {
                    return;
                }
                checkOther(player, event);
            }else if(plotPlayer.getLocation().isPlotArea()) {
                checkOther(player, event);
            }
        }
    }
    public void allShulker(Player p, PlayerInteractEvent event) {
        if (event.getClickedBlock().getType().equals(Material.SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.YELLOW_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.yellow")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.BLACK_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.black")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.BLUE_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.blue")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.BROWN_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.brown")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.CYAN_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.cyan")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.GRAY_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.gray")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.GREEN_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.green")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.LIGHT_BLUE_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.lightblue")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.LIGHT_GRAY_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.lightgray")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.LIME_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.lime")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.MAGENTA_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.magenta")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.ORANGE_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.orange")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.PINK_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.pink")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.PURPLE_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.purple")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.RED_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.red")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.WHITE_SHULKER_BOX)) {
            if (!p.hasPermission("zockerworld.plots.edit.shulkerbox.white")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
    }
    private void checkPerms(Player p, PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                if (!p.hasPermission(Permissions.editchest)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
                if (!p.hasPermission(Permissions.editredtonechest)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.DROPPER)) {
                if (!p.hasPermission(Permissions.editdropper)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.FURNACE)) {
                if (!p.hasPermission(Permissions.editfurnace)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.DISPENSER)) {
                if (!p.hasPermission(Permissions.editdispenser)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.HOPPER)) {
                if (!p.hasPermission(Permissions.edithopper)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.BEACON)) {
                if (!p.hasPermission(Permissions.editbeacon)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.BREWING_STAND)) {
                if (!p.hasPermission(Permissions.editbrewingstand)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if(event.getClickedBlock().getType().equals(Material.SMITHING_TABLE)) {
                if (!p.hasPermission(Permissions.editsmithingtable)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if(event.getClickedBlock().getType().equals(Material.BLAST_FURNACE)) {
                if (!p.hasPermission(Permissions.editblastfurnace)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if(event.getClickedBlock().getType().equals(Material.ANVIL)) {
                if (!p.hasPermission(Permissions.editanvil)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if(event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
                if (!p.hasPermission(Permissions.editenchantmenttable)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if(event.getClickedBlock().getType().equals(Material.GRINDSTONE)) {
                if (!p.hasPermission(Permissions.editgrindstone)) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            allShulker(p, event);
        }
    }
    private void checkOther(Player p, PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType().equals(EntityType.MINECART_HOPPER)) {
            if (!p.hasPermission(Permissions.edithoppercart)) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getRightClicked().getType().equals(EntityType.MINECART_CHEST)) {
            if (!p.hasPermission(Permissions.editchestcart)) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
    }
}
