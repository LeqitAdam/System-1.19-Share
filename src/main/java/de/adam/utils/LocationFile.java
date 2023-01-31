package utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LocationFile implements FileBase {

    public static HashMap<String, Location> warps = new HashMap<>();

    public static HashMap<String, Integer> teleported = new HashMap<>();

    private static Location spawn;

    public void load() {
        File file = new File("plugins/System/Warp/", "warps.yml");
        if (!file.exists()) {
            File folder = new File("plugins/System/Warp/");
            if (!folder.exists())
                folder.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addWarp(String name, Location location) {
        File file = new File("plugins/System/Warp/", "warps.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        List<String> list = configuration.getStringList("WarpNames");
        list.add(name);
        configuration.set("WarpNames", list);
        configuration.set("Warps." + name + ".Location", location);
        configuration.set("Warps." + name + ".Teleported", Integer.valueOf(0));
        warps.put(name, location);
        teleported.put(name, Integer.valueOf(0));
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSpawn(Location location) {
        File file = new File("plugins/System/Warp/", "warps.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("Spawn", location);
        setSpawnVariable(location);
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWarpString() {
        File file = new File("plugins/System/Warp/", "warps.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        List<String> list = configuration.getStringList("WarpNames");
        return String.valueOf(list).replace("[", "").replace("]", "");
    }

    public boolean existsWarp(String name) {
        return (warps.get(name) != null);
    }

    public void removeWarp(String name) {
        File file = new File("plugins/System/Warp/", "warps.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        warps.remove(name);
        teleported.remove(name);
        List<String> list = configuration.getStringList("WarpNames");
        list.remove(name);
        configuration.set("WarpNames", list);
        configuration.set("Warps." + name, null);
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWarps() {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin) PlaycenSystemV2.Main.getPlugin(PlaycenSystemV2.Main.class), new Runnable() {
            public void run() {
                File file = new File("plugins/System/Warp/", "warps.yml");
                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                List<String> list = configuration.getStringList("WarpNames");
                list.forEach(current -> {
                    LocationFile.teleported.put(current, Integer.valueOf(configuration.getInt("Warps." + current + ".Teleported")));
                    LocationFile.warps.put(current, (Location)configuration.get("Warps." + current + ".Location"));
                    Bukkit.getConsoleSender().sendMessage("loaded the warp + current");
                });
                LocationFile.this.setSpawnVariable((Location)configuration.get("Spawn"));
            }
        });
    }

    public static Location getSpawn() {
        return spawn;
    }

    private void setSpawnVariable(Location spawn) {
        LocationFile.spawn = spawn;
    }
}
