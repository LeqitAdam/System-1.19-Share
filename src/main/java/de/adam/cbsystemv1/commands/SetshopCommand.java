package de.adam.cbsystemv1.commands;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.shop.adminshop.villager.AdminshopVillager;
import de.adam.cbsystemv1.shop.adminshop.villager.VillagerHandler;
import de.adam.globalsystemv1.main.GlobalSystemSpigot;
import de.adam.globalsystemv1.utils.ReformatText;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetshopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("setshop")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission(Permissions.setvillager)) {
                    if(args.length == 1) {
                        if(args[0].equalsIgnoreCase("admin")) {
                            new AdminshopVillager(player.getLocation());
                            String villagername = VillagerHandler.getAdminshopname();
                            villagername = ReformatText.removeColorCodesAlternate(villagername);
                            File file = new File(GlobalSystemSpigot.pluginpath + "/villager/" + (villagername + ".yml"));
                            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                            String path = "location";
                            cfg.set(path, player.getLocation());
                            try {
                                cfg.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            player.sendMessage(ZockerWorldCBV1.prefix + " §7Der Villager wurde erstellt");
                        }else {
                            player.sendMessage("Shop noch nicht verfügbar. " + Messages.setvillagercommandusage);
                        }
                    } else player.sendMessage(ZockerWorldCBV1.prefix + Messages.setvillagercommandusage);
                }else player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
            }else sender.sendMessage(GlobalSystemSpigot.prefix + de.adam.globalsystemv1.files.Messages.onlyplayeruse);
        }
        return false;
    }
}
