package de.adam.listener;
import com.google.common.collect.Lists;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.PlotSquared;
import com.plotsquared.core.location.BlockLoc;
import com.plotsquared.core.location.PlotLoc;
import com.plotsquared.core.location.World;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.PlotItemStack;
import com.plotsquared.core.util.BlockUtil;
import org.bukkit.Location;
import org.bukkit.Material;
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
import java.util.List;
import java.util.UUID;
public class InventoryClickListener implements Listener {
    public static List<UUID> getNoClick() {
        return noClick;
    }
    public static List<UUID> noClick = Lists.newArrayList();
    private String adminperms = "plots.admin.interact.other";
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
        if (getNoClick().contains(event.getWhoClicked().getUniqueId())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBuild(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!PlotPlayer.from(player).getLocation().isPlotRoad()) {
            if(player.hasPermission(adminperms)) {
                if(!(PlotPlayer.from(player).getCurrentPlot().isOwner(player.getUniqueId()) || PlotPlayer.from(player).getCurrentPlot().isAdded(player.getUniqueId()))) {
                    checkPerms(player, event);
                }
            }
        }else if(player.hasPermission(adminperms)) {
            checkPerms(player, event);
        }
    }
    @EventHandler
    public void onEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(!PlotPlayer.from(player).getLocation().isPlotRoad()) {
            if(player.hasPermission(adminperms)) {
                if(!(PlotPlayer.from(player).getCurrentPlot().isOwner(player.getUniqueId())  || PlotPlayer.from(player).getCurrentPlot().isAdded(player.getUniqueId()))) {
                    checkOther(player, event);
                }
            }
        }else if(player.hasPermission(adminperms)) {
            checkOther(player, event);
        }
    }
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onClose(final InventoryCloseEvent event) {
        if (getNoClick().contains(event.getPlayer().getUniqueId())) {
            getNoClick().remove(event.getPlayer().getUniqueId());
        }
    }
    public void allShulker(Player p, PlayerInteractEvent event) {
        if (event.getClickedBlock().getType().equals(Material.SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.yellow")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.YELLOW_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.yellow")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.BLACK_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.black")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.BLUE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.blue")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.BROWN_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.brown")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.CYAN_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.cyan")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.GRAY_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.gray")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.GREEN_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.green")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.LIGHT_BLUE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.lightblue")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.LIGHT_GRAY_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.lightgray")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.LIME_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.lime")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.MAGENTA_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.magenta")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.ORANGE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.orange")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.PINK_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.pink")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.PURPLE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.purple")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.RED_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.red")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getClickedBlock().getType().equals(Material.WHITE_SHULKER_BOX)) {
            if (!p.hasPermission("system.plot.edit.shulkerbox.white")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
    }
    private void checkPerms(Player p, PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                if (!p.hasPermission("system.plot.edit.chest")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
                if (!p.hasPermission("system.plot.edit.redstonechest")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.DROPPER)) {
                if (!p.hasPermission("system.plot.edit.dropper")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.FURNACE)) {
                if (!p.hasPermission("system.plot.edit.furnace")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.DISPENSER)) {
                if (!p.hasPermission("system.plot.edit.dispenser")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.HOPPER)) {
                if (!p.hasPermission("system.plot.edit.hopper")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.BEACON)) {
                if (!p.hasPermission("system.plot.edit.beacon")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            if (event.getClickedBlock().getType().equals(Material.BREWING_STAND)) {
                if (!p.hasPermission("system.plot.edit.brewingstand")) {
                    getNoClick().add(event.getPlayer().getUniqueId());
                }
            }
            allShulker(p, event);
        }
    }
    private void checkOther(Player p, PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType().equals(EntityType.MINECART_HOPPER)) {
            if (!p.hasPermission("system.plot.edit.hopperminecart")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
        if (event.getRightClicked().getType().equals(EntityType.MINECART_CHEST)) {
            if (!p.hasPermission("system.plot.edit.chestminecart")) {
                getNoClick().add(event.getPlayer().getUniqueId());
            }
        }
    }
    public static void isBlockonUsersPlot(Player player, Block block) {
        PlotPlayer plotPlayer = PlotPlayer.from(player);
        PlotLoc loc = new PlotLoc(block.getX(), block.getY(), block.getZ());
        BlockLoc blockLoc = new BlockLoc(block.getX(), block.getY(), block.getZ());
        World world = plotPlayer.getLocation().getWorld();
        PlotAPI plotAPI = new PlotAPI();
        /*Plot plot = Plot.getPlot(loc);
        if(!plot.isOwner(plotPlayer.getUUID()) || !plot.isAdded(plotPlayer.getUUID())) {
            player.sendMessage("Plotblock get worked");
        }*/
        Block block2 = PlotPlayer.from(player).getPlatformPlayer().getTargetBlockExact(10);
        PlotPlayer plotBlock = PlotPlayer.from(block);
        Plot plot = Plot.getPlot(plotBlock.getLocation());
        if(!plot.isOwner(plotPlayer.getUUID())) {
            player.sendMessage("Test worked");
        }
        player.sendMessage("Methode ausfegührt");
    }
}
