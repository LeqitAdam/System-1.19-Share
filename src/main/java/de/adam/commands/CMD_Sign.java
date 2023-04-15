package de.adam.commands;

import de.adam.main.PlaycenSystemV2;
import de.adam.methods.SignManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class CMD_Sign implements CommandExecutor {
    private PlaycenSystemV2 plugin;
    public CMD_Sign(PlaycenSystemV2 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PlaycenSystemV2.pre + "Du musst ein Spieler sein.");
            return false;
        }
        final Player player = (Player) sender;
        if (player.hasPermission("sign.use")) {
            if (args.length >= 1) {
                ItemStack signItem = player.getItemInHand();
                if (player.getItemInHand().getType() != Material.AIR) {
                    if (player.getItemInHand().getAmount() == 1) {
                        final SignManager signManager = new SignManager(player.getItemInHand(), plugin);
                        if (args[0].equalsIgnoreCase("del")) {
                            if (signManager.isSigned(signItem)) {
                                player.setItemInHand(signManager.unSign(signItem));
                                player.sendMessage(PlaycenSystemV2.pre + "§7Du hast §aerfolgreich §7die §cSignatur entfernt!");
                            } else player.sendMessage(PlaycenSystemV2.pre + "§7Das §aItem §7wurde noch §cnicht signiert!");
                        } else {
                            final StringBuilder stringBuilder = new StringBuilder();
                            for (final String arg : args) {
                                stringBuilder.append(arg).append(" ");
                            }
                            if (!signManager.isSigned(signItem)) {
                                player.setItemInHand(signManager.sign(player.getName(), stringBuilder.toString()));
                                player.sendMessage(PlaycenSystemV2.pre + "§7Item §aerfolgreich §csigniert!");
                            } else player.sendMessage(PlaycenSystemV2.pre + "§7Das §aItem §7wurde breits §csigniert!");
                        }
                    } else player.sendMessage(PlaycenSystemV2.pre + "§7Du darfst nur ein §aItem §7gleichzeitig §csignieren!");
                } else player.sendMessage(PlaycenSystemV2.pre + "§7Du §cmusst §7ein §aItem §7in der Hand halten!");
            } else {
                player.sendMessage(PlaycenSystemV2.pre + "§cBitte benutze: §7/sign <Nachricht>");
                player.sendMessage(PlaycenSystemV2.pre + "§cBitte benutze: §7/sign del");
            }
        } else player.sendMessage(PlaycenSystemV2.noperm);
        return false;
    }
}
