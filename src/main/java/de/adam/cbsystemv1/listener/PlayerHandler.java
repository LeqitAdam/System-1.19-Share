package de.adam.cbsystemv1.listener;

import com.plotsquared.core.events.PlotFlagAddEvent;
import com.plotsquared.core.player.PlotPlayer;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.UUID;

public class PlayerHandler implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final File config = new File("plugins/Lobby-Zockerworld", "config.yml");
        final YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);

        e.setJoinMessage(null);
        Player p = e.getPlayer();
        try{
            World w = Bukkit.getWorld(conf.getString("Spawn.world"));
            Location loc = new Location(w, conf.getDouble("Spawn.x"), conf.getDouble("Spawn.y"), conf.getDouble("Spawn.z"));
            loc.setYaw((float) conf.getDouble("Spawn.yaw"));
            loc.setPitch((float) conf.getDouble("Spawn.pitch"));

            p.teleport(loc);
        } catch (Exception ex){
            p.sendMessage(ZockerWorldCBV1.prefix + " §cDer Spawn wurde noch nicht gesetzt!");
            p.sendMessage(ZockerWorldCBV1.prefix + " §c/setspawn");
        }
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }
}
