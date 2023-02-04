package de.adam.listener;

import de.adam.main.PlaycenSystemV2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class JoinListener implements Listener {

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
            p.sendMessage(PlaycenSystemV2.pre + " §cDer Spawn wurde noch nicht gesetzt!");
            p.sendMessage(PlaycenSystemV2.pre + " §c/setspawn");
        }
    }
}
