package de.adam.cbsystemv1.shop.adminshop.methods;

import com.google.common.collect.Lists;
import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.EconManager;
import de.adam.cbsystemv1.shop.adminshop.villager.VillagerHandler;
import de.adam.globalsystemv1.files.FileManager;
import de.adam.globalsystemv1.files.uuid;
import de.adam.globalsystemv1.main.GlobalSystemSpigot;
import de.adam.globalsystemv1.methods.InventoryManager;
import de.adam.globalsystemv1.methods.PermsManager;
import de.adam.globalsystemv1.utils.AdvancedItemStack;
import de.adam.globalsystemv1.utils.ReformatNumber;
import de.adam.globalsystemv1.utils.ReformatText;
import de.adam.globalsystemv1.utils.ReformatTime;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.List;

public class BuyManager {
    File config = new File(GlobalSystemSpigot.pluginpath, "config.yml");
    YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
    public void buyItem(Player player, Material material, double price, String itemname) {
        Economy econ = EconManager.getEconomy();
        String preis = ReformatNumber.formatCurrency(price);
        if(econ.getBalance(player) >= price) {
            EconomyResponse r = econ.withdrawPlayer(player, price);
            if(r.transactionSuccess()) {
                ItemStack buyedItem = new ItemStack(material);
                player.getInventory().addItem(buyedItem);
                player.sendMessage(ZockerWorldCBV1.prefix + "§7Du hast " + itemname + " §7für §b" + preis + " §7gekauft.");

                if(conf.getString(GlobalSystemSpigot.settingspath).equalsIgnoreCase("local")) {
                    String villagername = VillagerHandler.getAdminshopname();
                    villagername = ReformatText.removeColorCodesAlternate(villagername);
                    File shopFile = new File(GlobalSystemSpigot.pluginpath + "/villager/", villagername + ".yml");
                    YamlConfiguration shopConf = YamlConfiguration.loadConfiguration(shopFile);
                    String path = ReformatText.removeColorCodes(itemname);
                    int buyedamount = shopConf.getInt("Buyed-Counter." + path);
                    shopConf.set("Buyed-Counter." + path, buyedamount + 1);
                    FileManager.saveConfig(shopFile, shopConf);
                }else if(conf.getString(GlobalSystemSpigot.settingspath).equalsIgnoreCase("mysql")) {
                    //MySQL logic
                }else {
                    System.out.println("Please use Storage Type [MySQL or Local]");
                }
            } else player.sendMessage(String.format("An error occured: %s", r.errorMessage));
        }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.notenoughbalance);
    }
    public void buyEnchantedItem(Player player, ItemStack item, double price, String itemname) {
        Economy econ = EconManager.getEconomy();
        String preis = ReformatNumber.formatCurrency(price);
        if(econ.getBalance(player) >= price) {
            EconomyResponse r = econ.withdrawPlayer(player, price);
            if(r.transactionSuccess()) {
                String playername = PermsManager.getNamewithRank(player);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setLore(null);
                List<String> lore = Lists.newArrayList();
                lore.add("§7§m-----------------------------------");
                lore.add("§bDieses Item wurde original im §4Admin§cShop §bgekauft.");
                lore.add("§aGekauft §7von " + playername + " §7am §a" + ReformatTime.formatMillisToDateTime(System.currentTimeMillis()));
                itemMeta.setLore(lore);
                item.setItemMeta(itemMeta);
                player.getInventory().addItem(item);
                player.sendMessage(ZockerWorldCBV1.prefix + "§7Du hast " + itemname + " §7für §b" + preis + " §7gekauft.");

                if(conf.getString(GlobalSystemSpigot.settingspath).equalsIgnoreCase("local")) {
                    String villagername = VillagerHandler.getAdminshopname();
                    villagername = ReformatText.removeColorCodesAlternate(villagername);
                    File shopFile = new File(GlobalSystemSpigot.pluginpath + "/villager/", villagername + ".yml");
                    YamlConfiguration shopConf = YamlConfiguration.loadConfiguration(shopFile);
                    String path = itemMeta.getDisplayName();
                    path = ReformatText.removeColorCodes(path);
                    int buyedamount = shopConf.getInt("Buyed-Counter." + path);
                    shopConf.set("Buyed-Counter." + path, buyedamount + 1);
                    FileManager.saveConfig(shopFile, shopConf);
                }else if(conf.getString(GlobalSystemSpigot.settingspath).equalsIgnoreCase("mysql")) {
                    //MySQL logic
                }else {
                    System.out.println("Please use Storage Type [MySQL or Local]");
                }
            } else player.sendMessage(String.format("An error occured: %s", r.errorMessage));
        }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.notenoughbalance);
    }
}
