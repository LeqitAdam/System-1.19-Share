package de.adam.cbsystemv1.listener;

import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.globalsystemv1.methods.PermsManager;
import de.adam.globalsystemv1.utils.AdvancedItemStack;
import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItemHandler implements Listener {
    @EventHandler
    public void onRankRedeem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
        || event.getAction().equals(Action.LEFT_CLICK_AIR) ||event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            //Zocker Rang add
            if(AdvancedItemStack.getNBTTag(item) != null) {
                if(AdvancedItemStack.getNBTTag(item).equals("4067593")) {
                    event.setCancelled(true);
                    if(PermsManager.getSmallestSortID(player) > PermsManager.getGroup("Zocker").getSortId()) {
                        addZockerRang(player, item);
                    }else if(player.hasPermission(Permissions.bypassrankaddblock)) {
                        addZockerRang(player, item);
                    }else {
                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.hasrankalready);
                    }
                }
            }
        }
        //Weitere Ränge

    }
    private void addZockerRang(Player player, ItemStack item) {
        player.sendMessage(ZockerWorldCBV1.prefix + Messages.zockerrankadded);
        player.getInventory().removeItem(item);
        PermsManager.addGroup(player, "Zocker");
    }
}
