package de.adam.listener;

import com.google.common.collect.Lists;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.events.PlotPlayerEvent;
import com.plotsquared.core.location.BlockLoc;
import com.plotsquared.core.location.PlotLoc;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotItemStack;
import com.plotsquared.core.plot.PlotManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.swing.plaf.synth.Region;
import java.util.List;
import java.util.UUID;

public class InventoryClickListener implements Listener {

    public static List<UUID> getNoClick() {
        return noClick;
    }
    public static List<UUID> noClick = Lists.newArrayList();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onclick(final InventoryClickEvent e) {

        if(e.getCurrentItem() != null) {
            if (getNoClick().contains(e.getWhoClicked().getUniqueId())) {
                if(!e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {
                    e.setCancelled(true);
                }else if(e.getClick().isShiftClick()) {
                    e.setCancelled(true);
                }else if(e.getClick() == ClickType.DOUBLE_CLICK) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(final InventoryDragEvent event) {
        if (getNoClick().contains(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!PlotPlayer.from(player).getLocation().isPlotRoad()) {
            if(player.hasPermission("plots.admin.interact.other")) {
                if(!(PlotPlayer.from(player).getCurrentPlot().isOwner(player.getUniqueId()) || PlotPlayer.from(player).getCurrentPlot().isAdded(player.getUniqueId()))) {
                    checkPerms(player, event);
                }
                /*PlotPlayer plotPlayer = PlotPlayer.from(player);
                PlotAPI plotAPI = new PlotAPI();
                Block block = event.getClickedBlock();
                PlotLoc plotLoc = new PlotLoc(block.getX(), block.getY(), block.getZ());
                BlockLoc blockLoc = new BlockLoc(block.getX(), block.getY(), block.getZ());
                if(PlotPlayer.from(player).getCurrentPlot().getPosition().equals(blockLoc)) {
                    player.sendMessage("Block erkannt");
                }
                if(PlotPlayer.from(player).getLocation().equals(plotLoc)) {
                    player.sendMessage("Block erkannt 2");
                }*/
            }
        }else {
            if(player.hasPermission("plots.admin.interact.other")) {
                checkPerms(player, event);
            }
        }
    }
    public Location convertToPlotSquaredLocation(org.bukkit.Location bukkitLocation) {
        World worldName = bukkitLocation.getWorld();
        int x = bukkitLocation.getBlockX();
        int y = bukkitLocation.getBlockY();
        int z = bukkitLocation.getBlockZ();
        return new Location(worldName, x, y, z);
    }
    @EventHandler
    public void onEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(!PlotPlayer.from(player).getLocation().isPlotRoad()) {
            if(PlotPlayer.from(player).getCurrentPlot() != null) {
                if(!(PlotPlayer.from(player).getCurrentPlot().isOwner(player.getUniqueId())  || PlotPlayer.from(player).getCurrentPlot().isAdded(player.getUniqueId()))) {
                    checkOther(player, event);
                }
            }
        }else checkOther(player, event);
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onClose(final InventoryCloseEvent e) {
        if (getNoClick().contains(e.getPlayer().getUniqueId())) {
            getNoClick().remove(e.getPlayer().getUniqueId());
        }
    }

    public void allShulker(Player p, PlayerInteractEvent e) {
        if (e.getClickedBlock().getType().equals(Material.SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.yellow")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.YELLOW_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.yellow")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.BLACK_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.black")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.BLUE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.blue")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.BROWN_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.brown")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.CYAN_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.cyan")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.GRAY_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.gray")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.GREEN_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.green")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.LIGHT_BLUE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.lightblue")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.LIGHT_GRAY_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.lightgray")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.LIME_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.lime")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.MAGENTA_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.magenta")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.ORANGE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.orange")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.PINK_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.pink")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.PURPLE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.purple")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.RED_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.red")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getClickedBlock().getType().equals(Material.WHITE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.white")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
    }
    private void checkPerms(Player p, PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType().equals(Material.CHEST)) {
                if (!p.hasPermission("system.plot.edit.chest")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            if (e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
                if (!p.hasPermission("system.plot.edit.redstonechest")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            if (e.getClickedBlock().getType().equals(Material.DROPPER)) {
                if (!p.hasPermission("system.plot.edit.dropper")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            if (e.getClickedBlock().getType().equals(Material.FURNACE)) {
                if (!p.hasPermission("system.plot.edit.furnace")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            if (e.getClickedBlock().getType().equals(Material.DISPENSER)) {
                if (!p.hasPermission("system.plot.edit.dispenser")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            if (e.getClickedBlock().getType().equals(Material.HOPPER)) {
                if (!p.hasPermission("system.plot.edit.hopper")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            if (e.getClickedBlock().getType().equals(Material.BEACON)) {
                if (!p.hasPermission("system.plot.edit.beacon")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            if (e.getClickedBlock().getType().equals(Material.BREWING_STAND)) {
                if (!p.hasPermission("system.plot.edit.brewingstand")) {
                    getNoClick().add(e.getPlayer().getUniqueId());
                }
            }
            allShulker(p, e);
        }
    }
    private void checkOther(Player p, PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.MINECART_HOPPER)) {
            if (!p.hasPermission("system.plot.edit.hopperminecart")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
        if (e.getRightClicked().getType().equals(EntityType.MINECART_CHEST)) {
            if (!p.hasPermission("system.plot.edit.chestminecart")) {
                getNoClick().add(e.getPlayer().getUniqueId());
            }
        }
    }
}
