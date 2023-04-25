package de.adam.cbsystemv1.listener;

import de.adam.cbsystemv1.extras.ColorHandler;
import de.adam.cbsystemv1.extras.FilterHandler;
import de.adam.cbsystemv1.extras.AnvilTask;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RepairHandler implements Listener {

    @EventHandler
    public void onAnvilGUIClick(final InventoryClickEvent event) {
        if (event.getInventory().getType() == InventoryType.ANVIL && event.getWhoClicked() instanceof Player) {
            final Player player = (Player) event.getWhoClicked();
            final AnvilInventory inv = (AnvilInventory) event.getInventory();
            AnvilTask task = AnvilTask.getTask(inv);
            if (task == null) {
                task = new AnvilTask(inv, player);
            }
            if (event.getRawSlot() == 2) {
                final ItemStack translatedItem = ColorHandler.getTranslatedItem(player, inv, task);
                final List<String> illegalWords = FilterHandler.getIllegalWordsInItemName(translatedItem);
                if (illegalWords.size() != 0) {
                    event.setCancelled(true);
                    player.setExp(player.getExp());
                    for (final String word : illegalWords) {
                        player.sendMessage(ChatColor.RED + "Your item cannot contain the word " + ChatColor.BOLD + word
                                + ChatColor.RED + ".");
                    }
                }
                event.setCurrentItem(translatedItem);
            }
        }
    }
}
