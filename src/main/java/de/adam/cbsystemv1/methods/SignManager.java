package de.adam.cbsystemv1.methods;

import com.google.common.collect.Lists;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.text.SimpleDateFormat;
import java.util.List;

public class SignManager {
    private ItemStack itemStack;
    private ZockerWorldCBV1 plugin;

    public SignManager(final ItemStack itemStack, ZockerWorldCBV1 plugin) {
        this.plugin = plugin;
        this.itemStack = itemStack;
    }

    public ItemStack sign(final String name, final String message) {
        if (!this.isSigned(itemStack)) {
            this.setSigned(itemStack, true);
        }
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        List<String> lore;
        if (itemMeta.getLore() == null) {
            lore = Lists.newArrayList();
        } else {
            lore = (List<String>) itemMeta.getLore();
        }
        lore.add("§7§m-----------------------------------");
        lore.add("§7" + message.replace('&', '§'));
        lore.add("§7Signiert von §c" + name + " §7am §c" + this.fortmatTime(System.currentTimeMillis()));
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
        return this.itemStack;
    }

    public ItemStack unSign(ItemStack item) {
        if (this.isSigned(item)) {
            this.setSigned(item, false);
        }
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        final List<String> lore = (List<String>) itemMeta.getLore();
        for (int i = 0; i < 3; ++i) {
            lore.remove(lore.size() - 1);
        }
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
        return this.itemStack;
    }
    public boolean isSigned(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return false;
        }
        Double signed = meta.getPersistentDataContainer().getOrDefault(
                new NamespacedKey(plugin, "signed"),
                PersistentDataType.DOUBLE,
                0.0
        );
        return signed == 1.0;
    }

    public void setSigned(ItemStack itemStack, boolean signed) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.getPersistentDataContainer().set(
                new NamespacedKey(plugin, "signed"),
                PersistentDataType.DOUBLE,
                signed ? 1.0 : 0.0
        );
        itemStack.setItemMeta(meta);
    }

    private String fortmatTime(final Long millis) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return simpleDateFormat.format(millis);
    }
}
