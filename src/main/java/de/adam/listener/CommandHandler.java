package de.adam.listener;

import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import de.adam.globalsystemv1.main.GlobalSystem;
import de.adam.main.ZockerWorldCBV1;
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
    public void sendCMD(final PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String[] cmd = e.getMessage().substring(1).split(" ");
        if(cmd[0].startsWith("p") || cmd[0].startsWith("plots") || cmd[0].startsWith("plot") ||cmd[0].startsWith("plotsquared")
        || cmd[0].startsWith("plotme") || cmd[0].startsWith("p2") || cmd[0].startsWith("2") || cmd[0].startsWith("ps")) {
            if(cmd[1].equalsIgnoreCase("so") || cmd[1].equalsIgnoreCase("setowner")) {
                Plot plot = PlotPlayer.from(p).getCurrentPlot();
                PlotPlayer plotPlayer = PlotPlayer.from(p);
                OfflinePlayer target = Bukkit.getOfflinePlayer(cmd[2]);
                IPermissionUser permuser = CloudNetDriver.getInstance().getPermissionManagement().getUser(target.getUniqueId());
                PlotPlayer plotTarget = PlotPlayer.from(permuser);
                if(!plot.isOwner(plotPlayer.getUUID())) {
                    if(!p.hasPermission("system.admin.plot.setowner")) {
                        e.setCancelled(true);
                        p.sendMessage("§8[§6P2§8] §cYou are lacking the permission node:");
                        p.sendMessage(" §6plots.admin.command.setowner§c.");
                    }else if(plotTarget.hasPermission("system.admin.plot.setowner.teamler") || plotTarget.hasPermission("system.admin.plot.setowner.all")) {
                        e.setCancelled(true);
                        p.sendMessage(ZockerWorldCBV1.prefix + " §cDiesem Spieler darfst du keine Plots geben!");
                    }
                }else if(cmd[2].equalsIgnoreCase("-")) {
                    if(!p.hasPermission("system.admin.plot.setowner.all")) {
                        e.setCancelled(true);
                        p.sendMessage(ZockerWorldCBV1.prefix + " §cDuplizieren ist verboten!");
                    }
                }else if(!(plotTarget.hasPermission("system.admin.plot.setowner.teamler") || plotTarget.hasPermission("system.admin.plot.setowner.all"))) {
                    e.setCancelled(true);
                    p.sendMessage(ZockerWorldCBV1.prefix + " §cDiesem Spieler darfst du keine Plots geben!");
                }
            }
            if(cmd[1].equalsIgnoreCase("flag") && cmd[3].equalsIgnoreCase("gamemode")) {
                if(!p.hasPermission("*")) {
                    e.setCancelled(true);
                    p.sendMessage(ZockerWorldCBV1.prefix + "§cVersuch nicht die Wirtschaft zu zerstören.");
                }
            }
        }
    }
}
