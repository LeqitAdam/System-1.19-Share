package de.adam.listener;

import com.google.common.collect.Lists;
import com.plotsquared.core.player.PlotPlayer;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(final InventoryDragEvent e) {
        if (getNoClick().contains(e.getWhoClicked().getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBuild(final PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(PlotPlayer.from(p).getLocation().isPlotRoad() || !(PlotPlayer.from(p).getCurrentPlot().isOwner(p.getUniqueId()) || PlotPlayer.from(p).getCurrentPlot().isAdded(p.getUniqueId()))) {
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
    }

    @EventHandler
    public void onEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if(PlotPlayer.from(p).getLocation().isPlotRoad() || !(PlotPlayer.from(p).getCurrentPlot().isOwner(p.getUniqueId())  || PlotPlayer.from(p).getCurrentPlot().isAdded(p.getUniqueId()))) {
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
}
