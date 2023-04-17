package de.adam.listener;

import com.plotsquared.core.player.PlotPlayer;
import de.adam.main.ZockerWorldCBV1;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSendListener implements Listener {

    @EventHandler
    public void sendCMD(final PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        String[] cmd = e.getMessage().substring(1).split(" ");
        if(cmd[0].startsWith("p") || cmd[0].startsWith("plots") || cmd[0].startsWith("plot") ||cmd[0].startsWith("plotsquared")
        || cmd[0].startsWith("plotme") || cmd[0].startsWith("p2") || cmd[0].startsWith("2") || cmd[0].startsWith("ps")) {
            if(cmd[1].equalsIgnoreCase("so") || cmd[1].equalsIgnoreCase("setowner")) {
                if(!PlotPlayer.from(p).getCurrentPlot().isOwner(p.getUniqueId())) {
                        if(!p.hasPermission("system.admin.plot.setowner")) {
                            e.setCancelled(true);
                            p.sendMessage("§8[§6P2§8] §cYou are lacking the permission node:");
                            p.sendMessage(" §6plots.admin.command.setowner§c.");
                        }else if(!(p.hasPermission("system.admin.plot.setowner.teamler") || p.hasPermission("system.admin.plot.setowner.all"))) {
                            e.setCancelled(true);
                            p.sendMessage(ZockerWorldCBV1.prefix + " §cDiesem Spieler darfst du keine Plots geben!");
                        }
                }else if(cmd[2].equalsIgnoreCase("-")) {
                    if(!p.hasPermission("system.admin.plot.setowner.all")) {
                        e.setCancelled(true);
                        p.sendMessage(ZockerWorldCBV1.prefix + " §cDuplizieren ist verboten!");
                    }
                }
            }
        }
    }
}
