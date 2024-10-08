package de.adam.cbsystemv1.commands;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.methods.SignManager;
import de.adam.globalsystemv1.methods.PermsManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class SignCommand implements CommandExecutor {
    private ZockerWorldCBV1 plugin;
    public SignCommand(ZockerWorldCBV1 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.onlyplayeruse);
            return false;
        }
        final Player player = (Player) sender;
        if (player.hasPermission(Permissions.signcommand)) {
            if (args.length >= 1) {
                ItemStack signItem = player.getItemInHand();
                if (player.getItemInHand().getType() != Material.AIR) {
                    if (player.getItemInHand().getAmount() == 1) {
                        final SignManager signManager = new SignManager(player.getItemInHand(), plugin);
                        if (args[0].equalsIgnoreCase("del") || args[0].equalsIgnoreCase("remove")) {
                            if(player.hasPermission(Permissions.signdel)) {
                                if (signManager.isSigned(signItem)) {
                                    player.setItemInHand(signManager.unSign(signItem));
                                    player.sendMessage(ZockerWorldCBV1.prefix + Messages.signremoved);
                                }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.signitemnotsigned);
                            }else player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
                        }else {
                            final StringBuilder stringBuilder = new StringBuilder();
                            for (final String arg : args) {
                                stringBuilder.append(arg).append(" ");
                            }
                            if (!signManager.isSigned(signItem)) {
                                String playername = PermsManager.getNamewithRank(player);
                                player.setItemInHand(signManager.sign(playername, stringBuilder.toString()));
                                player.sendMessage(ZockerWorldCBV1.prefix + Messages.signadded);
                            }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.signedalready);
                        }
                    }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.signonlyone);
                }else player.sendMessage(ZockerWorldCBV1.prefix + Messages.signholditeminhand);
            }else {
                player.sendMessage(ZockerWorldCBV1.prefix + Messages.signcommandusage);
                player.sendMessage(ZockerWorldCBV1.prefix + Messages.signcommanddelusage);
            }
        }else player.sendMessage(de.adam.globalsystemv1.files.Messages.noperm);
        return false;
    }
}
