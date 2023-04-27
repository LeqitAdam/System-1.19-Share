package de.adam.cbsystemv1.methods;

import com.plotsquared.core.configuration.ConfigurationUtil;
import com.plotsquared.core.player.PlotPlayer;
import com.plotsquared.core.plot.Plot;
import com.sk89q.worldedit.function.pattern.Pattern;
import de.adam.cbsystemv1.commands.CustomItemCommand;
import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import de.adam.globalsystemv1.main.GlobalSystemSpigot;
import de.adam.globalsystemv1.methods.CountdownManager;
import de.adam.globalsystemv1.utils.ReformatTime;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlotManager {
    public static void modifyPlot(Plot plot, Player player, String permission, String patternString, String type) {
        try {
            if(PlotPlayer.from(player).getCurrentPlot() != null) {
                if(!plot.isOwner(player.getUniqueId()) && !player.hasPermission(Permissions.randcommandbypass)) {
                    player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.userisnotplotowner);
                    return;
                }
                if(player.hasPermission(permission)) {
                    if(CountdownManager.checkCountdown(player, "randcooldown", "cooldown") == false || player.hasPermission(Permissions.randcommandbypass)) {
                        final Pattern pattern = ConfigurationUtil.BLOCK_BUCKET.parseString(patternString).toPattern();
                        if (plot.getConnectedPlots().size() > 1) {
                            for (final Plot plots : plot.getConnectedPlots()) {
                                plots.getPlotModificationManager().setComponent(type, pattern, null, null);
                            }
                            CountdownManager.startCountdown(player, 300, "randcooldown", "cooldown");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            String rand = CustomItemCommand.getName(patternString);
                            player.sendMessage(ZockerWorldCBV1.prefix + "§7Dein Rand wurde auf " + rand + " §7gesetzt.");
                        } else {
                            plot.getPlotModificationManager().setComponent(type, pattern, null, null);
                            CountdownManager.startCountdown(player, 300, "randcooldown", "cooldown");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            String rand = CustomItemCommand.getName(patternString);
                            player.sendMessage(ZockerWorldCBV1.prefix + "§7Dein Rand wurde auf " + rand + " §7gesetzt.");
                        }
                    }else {
                        int totalseconds = CountdownManager.remainingCountdown(player, "randcooldown", "cooldown");
                        if(totalseconds >= 60) {
                            int time[] = ReformatTime.splitSeconds(totalseconds);
                            int minutes = time[2];
                            player.sendMessage(GlobalSystemSpigot.prefix + "§7Du musst noch §a" + minutes + " Minute(n) §7warten.");
                            CountdownManager.startCountdown(player, 120, "randcooldown", "cmdusedonce");
                        }else {
                            player.sendMessage(GlobalSystemSpigot.prefix + "§7Du musst nur noch §a" + totalseconds + " Sekunde(n) §7warten!");
                            CountdownManager.startCountdown(player, 120, "randcooldown", "cmdusedonce");
                        }
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                        player.closeInventory();
                    }
                }else {
                    player.sendMessage(ZockerWorldCBV1.prefix + Messages.randcommandnoperms);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                    player.closeInventory();
                }

            }else {
                player.closeInventory();
                player.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.notinaplot);
            }
        } catch (Throwable t) {
            throw new RuntimeException("Error while modifying plot with pattern '" + patternString + "'.", t);
        }
    }
}
