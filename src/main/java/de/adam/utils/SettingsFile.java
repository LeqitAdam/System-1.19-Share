package utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SettingsFile implements FileBase {

    private static String warpPermission;

    private static String certainWarpPermission;

    private static String removeWarpPermission;

    private static String setSpawnPermission;

    private static String setWarpPermission;

    private static String warpOverviewPermission;

    private static String spawnPermission;

    private static boolean mustWait;

    private static boolean teleportToSpawnOnJoin;

    private static boolean useSpawnSystem;

    private static int delayInSeconds;

    public void load() {
        File file = new File("plugins/System/Warp/", "settings.yml");
        if (!file.exists()) {
            File folder = new File("plugins/System/Warp/");
            if (!folder.exists())
                folder.mkdirs();
            try {
                file.createNewFile();
                writeDefault(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeDefault(File file) {
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("Warping.MustWait", Boolean.valueOf(true));
        configuration.set("Warping.DelayInSeconds", Integer.valueOf(5));
        configuration.set("Spawn.TeleportOnJoin", Boolean.valueOf(true));
        configuration.set("Spawn.UseSystem", Boolean.valueOf(true));
        configuration.set("Permissions.WarpGeneral", "warp.use");
        configuration.set("Permissions.CertainWarp", "warp.custom.%name%");
        configuration.set("Permissions.SetWarp", "warp.set");
        configuration.set("Permissions.RemoveWarp", "warp.remove");
        configuration.set("Permissions.SetSpawn", "warp.setspawn");
        configuration.set("Permissions.WarpOverview", "warp.overview");
        configuration.set("Permissions.Spawn", "warp.spawn");
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSettings() {
        File file = new File("plugins/System/Warp/", "settings.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        setMustWait(configuration.getBoolean("Warping.MustWait"));
        setDelayInSeconds(configuration.getInt("Warping.DelayInSeconds"));
        setTeleportToSpawnOnJoin(configuration.getBoolean("Spawn.TeleportOnJoin"));
        setUseSpawnSystem(configuration.getBoolean("Spawn.UseSystem"));
        setWarpPermission(configuration.getString("Permissions.WarpGeneral"));
        setCertainWarpPermission(configuration.getString("Permissions.CertainWarp"));
        setSetWarpPermission(configuration.getString("Permissions.SetWarp"));
        setRemoveWarpPermission(configuration.getString("Permissions.RemoveWarp"));
        setSetSpawnPermission(configuration.getString("Permissions.SetSpawn"));
        setWarpOverviewPermission(configuration.getString("Permissions.WarpOverview"));
        setSpawnPermission(configuration.getString("Permissions.Spawn"));
    }

    public static boolean isUseSpawnSystem() {
        return useSpawnSystem;
    }

    public static void setUseSpawnSystem(boolean useSpawnSystem) {
        SettingsFile.useSpawnSystem = useSpawnSystem;
    }

    public static String getSpawnPermission() {
        return spawnPermission;
    }

    public static void setSpawnPermission(String spawnPermission) {
        SettingsFile.spawnPermission = spawnPermission;
    }

    public static String getWarpPermission() {
        return warpPermission;
    }

    public static void setWarpPermission(String warpPermission) {
        SettingsFile.warpPermission = warpPermission;
    }

    public static String getCertainWarpPermission() {
        return certainWarpPermission;
    }

    public static void setCertainWarpPermission(String certainWarpPermission) {
        SettingsFile.certainWarpPermission = certainWarpPermission;
    }

    public static String getRemoveWarpPermission() {
        return removeWarpPermission;
    }

    public static void setRemoveWarpPermission(String removeWarpPermission) {
        SettingsFile.removeWarpPermission = removeWarpPermission;
    }

    public static String getSetSpawnPermission() {
        return setSpawnPermission;
    }

    public static void setSetSpawnPermission(String setSpawnPermission) {
        SettingsFile.setSpawnPermission = setSpawnPermission;
    }

    public static String getSetWarpPermission() {
        return setWarpPermission;
    }

    public static void setSetWarpPermission(String setWarpPermission) {
        SettingsFile.setWarpPermission = setWarpPermission;
    }

    public static boolean isMustWait() {
        return mustWait;
    }

    public static void setMustWait(boolean mustWait) {
        SettingsFile.mustWait = mustWait;
    }

    public static boolean isTeleportToSpawnOnJoin() {
        return teleportToSpawnOnJoin;
    }

    public static void setTeleportToSpawnOnJoin(boolean teleportToSpawnOnJoin) {
        SettingsFile.teleportToSpawnOnJoin = teleportToSpawnOnJoin;
    }

    public static int getDelayInSeconds() {
        return delayInSeconds;
    }

    public static void setDelayInSeconds(int delayInSeconds) {
        SettingsFile.delayInSeconds = delayInSeconds;
    }

    public static String getWarpOverviewPermission() {
        return warpOverviewPermission;
    }

    public static void setWarpOverviewPermission(String warpOverviewPermission) {
        SettingsFile.warpOverviewPermission = warpOverviewPermission;
    }

}
