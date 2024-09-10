package de.adam.cbsystemv1.listener;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.commands.WarpNotFoundException;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.ItemManager;
import de.adam.cbsystemv1.methods.PlotManager;
import de.adam.cbsystemv1.shop.files.Names;
import net.ess3.api.InvalidWorldException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler implements Listener {
    Essentials essentials = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
    public Location getWarpLocation(String warp) throws WarpNotFoundException, InvalidWorldException {
        // Replace "warp_name" with the name of the warp associated with the item.
        return essentials.getWarps().getWarp(warp);
    }
    //Warp inv
    @EventHandler
    public void warpInvClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if(event.getView().getTitle().equals(Names.warpinvname)) {
            ItemManager items = new ItemManager();
            if(event.getCurrentItem().equals(items.getSpawnWarpItem())) {
                Location warpLocation;
                try {
                    warpLocation = getWarpLocation("Spawn");
                } catch (WarpNotFoundException e) {
                    player.closeInventory();
                    player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpnotfound);
                    throw new RuntimeException(e);
                } catch (InvalidWorldException e) {
                    throw new RuntimeException(e);
                }
                player.teleport(warpLocation);
                player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpcommandteleported1 + "Spawn" + Messages.warpcommandteleported2);
            }
            if(event.getCurrentItem().equals(items.getCBWarpItem())) {
                Location warpLocation;
                try {
                    warpLocation = getWarpLocation("CityBuild");
                } catch (WarpNotFoundException e) {
                    player.closeInventory();
                    player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpnotfound);
                    throw new RuntimeException(e);
                } catch (InvalidWorldException e) {
                    throw new RuntimeException(e);
                }
                player.teleport(warpLocation);
                player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpcommandteleported1 + "CityBuild" + Messages.warpcommandteleported2);
            }
            if(event.getCurrentItem().equals(items.getFarmweltWarpItem())) {
                Location warpLocation;
                try {
                    warpLocation = getWarpLocation("Farmwelt");
                } catch (WarpNotFoundException e) {
                    player.closeInventory();
                    player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpnotfound);
                    throw new RuntimeException(e);
                } catch (InvalidWorldException e) {
                    throw new RuntimeException(e);
                }
                player.teleport(warpLocation);
                player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpcommandteleported1 + "Farmwelt" + Messages.warpcommandteleported2);
            }
            if(event.getCurrentItem().equals(items.getNetherWarpItem())) {
                Location warpLocation;
                try {
                    warpLocation = getWarpLocation("Nether");
                } catch (WarpNotFoundException e) {
                    player.closeInventory();
                    player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpnotfound);
                    throw new RuntimeException(e);
                } catch (InvalidWorldException e) {
                    throw new RuntimeException(e);
                }
                player.teleport(warpLocation);
                player.sendMessage(ZockerWorldCBV1.prefix + Messages.warpcommandteleported1 + "Nether" + Messages.warpcommandteleported2);
            }
        }
    }
    @EventHandler
    public void randInvClick(InventoryClickEvent event) {
        if(event.getCurrentItem() == null) {
            return;
        }
        if(event.getView().getTitle().equals(Names.randinvname)) {
            Player player = (Player) event.getWhoClicked();
            Plot plot = PlotPlayer.from(player).getCurrentPlot();
            ItemStack clickedItem = event.getCurrentItem();
            ItemManager items = new ItemManager();
            String type = "border";
            //1st row
            if(clickedItem.equals(items.getRandDefault())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommanddef, "polished_andesite", type);
            }
            if(clickedItem.equals(items.getRandGold())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandgold, "gold_block", type);
            }
            if(clickedItem.equals(items.getRandSmaragd())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandsmaragd, "emerald_block", type);
            }
            if(clickedItem.equals(items.getRandDia())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommanddia, "diamond_block", type);
            }
            if(clickedItem.equals(items.getRandObsidian())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandobi, "obsidian", type);
            }
            if(clickedItem.equals(items.getRandEndportalframe())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandendportalframe, "end_portal_frame", type);
            }
            if(clickedItem.equals(items.getRandEnderstab())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandendstab, "end_rod", type);
            }
            if(clickedItem.equals(items.getRandBookshelf())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandbookshelf, "bookshelf", type);
            }
            if(clickedItem.equals(items.getRandCobweb())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandcobweb, "cobweb", type);
            }
            //2nd row
            if(clickedItem.equals(items.getRandBeacon())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandbeacon, "beacon", type);
            }
            if(clickedItem.equals(items.getRandEnchanter())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandenchanter, "enchanting_table", type);
            }
            if(clickedItem.equals(items.getRandLantern())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandsealaterne, "sea_lantern", type);
            }
            if(clickedItem.equals(items.getRandGlowstone())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandglowstone, "glowstone", type);
            }
            if(clickedItem.equals(items.getRandGildedBlackstone())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandgildedblackstone, "gilded_blackstone", type);
            }
            if(clickedItem.equals(items.getRandCryingObi())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandcryingobi, "crying_obsidian", type);
            }
            if(clickedItem.equals(items.getRandMagmablock())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandmagmablock, "magma_block", type);
            }
            if(clickedItem.equals(items.getRandJackOLantern())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandjackolantern, "jack_o_lantern", type);
            }
            if(clickedItem.equals(items.getRandCampfire())) {
                PlotManager.modifyPlot(plot, player, Permissions.randcommandcampfire, "campfire", type);
            }
        }
    }
}
