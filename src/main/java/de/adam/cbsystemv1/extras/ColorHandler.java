package de.adam.cbsystemv1.extras;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ColorHandler {

    public static final int INPUT_LEFT = 0;
    public static final int OUTPUT = 2;

    public static boolean hasColor(final String s, final ChatColor c) {
        return s.contains(new StringBuilder().append(c).toString());
    }

    public static boolean hasColorPermission(final Player p, final ChatColor c) {
        return hasColorPermission(p, c.getChar());
    }

    public static boolean hasColorPermission(final Player p, final char c) {
        return p.hasPermission("coloredanvils.*")
                || p.hasPermission("coloredanvils.color.*") || p.hasPermission("coloredanvils.color." + c)
                || p.hasPermission("coloredanvils.color.&" + c);
    }

    public static ItemStack getTranslatedItem(final Player p, final AnvilInventory inv, final AnvilTask task) {
        final ItemStack outputItem = inv.getItem(2);
        if (outputItem != null && outputItem.hasItemMeta()) {
            final ItemMeta outputItemMeta = outputItem.getItemMeta();
            if (outputItemMeta.hasDisplayName()) {
                final ItemStack inputItem = inv.getItem(0);
                if (inputItem != null && inputItem.hasItemMeta()) {
                    final ItemMeta inputItemMeta = inputItem.getItemMeta();
                    if (inputItemMeta.hasDisplayName() && outputItemMeta.getDisplayName().replaceAll("&", "")
                            .replaceAll("§", "").equals(inputItemMeta.getDisplayName().replaceAll("§", ""))) {
                        outputItemMeta.setDisplayName(inputItemMeta.getDisplayName());
                        outputItem.setItemMeta(outputItemMeta);
                        return p.hasPermission("test") ? colorItemWithPermissions(outputItem, p)
                                : outputItem;
                    }
                }
                return colorItemWithPermissions(outputItem, p);
            }
        }
        return outputItem;
    }

    public static ItemStack colorItemWithPermissions(final ItemStack item, final Player p) {
        final ItemMeta itemMeta = item.getItemMeta();
        String coloredName = ChatColor.translateAlternateColorCodes('&', itemMeta.getDisplayName());
        for (int i = 0; i < coloredName.length(); ++i) {
            if (coloredName.charAt(i) == '§') {
                final char c = coloredName.charAt(i + 1);
                if (!hasColorPermission(p, c)) {
                    coloredName = coloredName.replaceAll("§" + c, "&" + c);
                }
            }
        }
        itemMeta.setDisplayName(coloredName);
        item.setItemMeta(itemMeta);
        return item;
    }
}
