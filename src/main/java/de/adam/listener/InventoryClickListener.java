package de.adam.listener;

import com.google.common.collect.Lists;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.listener.PlotListener;
import com.plotsquared.core.player.ConsolePlayer;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotArea;
import com.plotsquared.core.plot.PlotHandler;
import com.plotsquared.core.plot.PlotManager;
import de.adam.main.PlaycenSystemV2;
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
        if(PlotPlayer.from(p).getLocation().isPlotRoad() || !PlotPlayer.from(p).getCurrentPlot().isOwner(p.getUniqueId())) {
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
            }
        }
    }

    @EventHandler
    public void onEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if(PlotPlayer.from(p).getLocation().isPlotRoad() || !PlotPlayer.from(p).getCurrentPlot().isOwner(p.getUniqueId())) {
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
}
