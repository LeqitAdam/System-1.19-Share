package de.adam.cbsystemv1.listener;

import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.globalsystemv1.files.Messages;
import de.adam.globalsystemv1.main.GlobalSystemSpigot;
import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.permission.IPermissionUser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandHandler implements Listener {

    @EventHandler
    public void sendCMD(final PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] cmd = event.getMessage().substring(1).split(" ");

        if(cmd[0].startsWith("essentials") || cmd[0].toLowerCase().contains("essentials:") || cmd[0].toUpperCase().contains("essentials:")
                || cmd[0].toLowerCase().contains("essentials") || cmd[0].startsWith("give") || cmd[0].equalsIgnoreCase("i")) {
            if(!player.hasPermission(Permissions.bypassadmincommands)) {
                event.setCancelled(true);
                player.sendMessage(GlobalSystemSpigot.prefix + Messages.commandnotfound);
            }
        }
        if(cmd.length == 1) {
            if(cmd[0].equalsIgnoreCase("ec") || cmd[0].equalsIgnoreCase("echest") || cmd[0].equalsIgnoreCase("enderchest")) {
                event.setCancelled(true);
                player.performCommand("ec open");
            }
        }
        if(cmd.length == 2) {
            if(cmd[0].equalsIgnoreCase("ec")) {
                if(cmd[1].equals("open")) {
                    return;
                }
                if(cmd[1].equals("delete")) {
                    return;
                }
                if(cmd[1].equals("reload")) {
                    return;
                }
                event.setCancelled(true);
                String uuid = cmd[1];
                player.performCommand("ec open " + uuid);
            }

        }
        if(cmd.length > 2) {
            if(cmd[0].equalsIgnoreCase("ec")) {
                if(cmd[1].equals("open")) {
                    return;
                }
                if(cmd[1].equalsIgnoreCase("delete")) {
                    if(player.hasPermission("*")) {
                        event.setCancelled(false);
                    }else {
                        event.setCancelled(true);
                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.noperm);
                    }
                    return;
                }
                if(cmd[1].equalsIgnoreCase("reload")) {
                    if(player.hasPermission("*")) {
                        event.setCancelled(false);
                    }else {
                        event.setCancelled(true);
                        player.sendMessage(ZockerWorldCBV1.prefix + Messages.noperm);
                    }
                }
            }
            if(cmd[0].startsWith("p") || cmd[0].startsWith("plots") || cmd[0].startsWith("plot") ||cmd[0].startsWith("plotsquared")
                    || cmd[0].startsWith("plotme") || cmd[0].startsWith("p2") || cmd[0].startsWith("2") || cmd[0].startsWith("ps")) {
                if(cmd[1].equalsIgnoreCase("so") || cmd[1].equalsIgnoreCase("setowner")) {
                    Plot plot = PlotPlayer.from(player).getCurrentPlot();
                    PlotPlayer plotPlayer = PlotPlayer.from(player);
                    OfflinePlayer target = Bukkit.getOfflinePlayer(cmd[2]);
                    PlotPlayer plotTarget = PlotPlayer.from(target);
                    if(!plot.isOwner(plotPlayer.getUUID())) {
                        if(!player.hasPermission("zockerworld.plot.admin.setowner")) {
                            event.setCancelled(true);
                            player.sendMessage("§8[§6P2§8] §cYou are lacking the permission node:");
                            player.sendMessage(" §6plots.admin.command.setowner§c.");
                        }else if(plotTarget.hasPermission("system.admin.plot.setowner.teamler") || plotTarget.hasPermission("system.admin.plot.setowner.all")) {
                            if(!player.hasPermission("zockerworld.plot.admin")) {
                                event.setCancelled(true);
                                player.sendMessage(ZockerWorldCBV1.prefix + " §cDiesem Spieler darfst du keine Plots geben!");
                            }
                        }
                    }else if(cmd[2].equalsIgnoreCase("-")) {
                        if(!player.hasPermission("system.admin.plot.setowner.all")) {
                            event.setCancelled(true);
                            player.sendMessage(ZockerWorldCBV1.prefix + " §cDuplizieren ist verboten!");
                        }
                    }else if(!player.hasPermission("zockerworld.plot.setowner.*")) {
                        event.setCancelled(true);
                        player.sendMessage(ZockerWorldCBV1.prefix + " §cDiesem Spieler darfst du keine Plots geben!");
                    }
                }
                if(cmd[1].equalsIgnoreCase("flag") && cmd[3].equalsIgnoreCase("gamemode")) {
                    if(!player.hasPermission("*")) {
                        event.setCancelled(true);
                        player.sendMessage(ZockerWorldCBV1.prefix + "§cVersuch nicht die Wirtschaft zu zerstören.");
                    }
                }
            }
        }

    }
}
