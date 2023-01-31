package de.adam.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageFile implements FileBase {

    private static String prefix;
    /*     */   private static String noPermission;
    /*     */   private static String noPlayer;
    /*     */   private static String warpDoesNotExist;
    /*     */   private static String warpAlreadyExists;
    /*     */   private static String warpCreated;
    /*     */   private static String warpRemoved;
    /*     */   private static String spawnSet;
    /*     */   private static String warped;
    /*     */   private static String mustWait;
    /*     */   private static String warpOverview;
    /*     */   private static String usageSetSpawn;
    /*     */   private static String usageSetWarp;
    /*     */   private static String usageRemoveWarp;
    /*     */   private static String usageWarp;
    /*     */   private static String noSpawnYet;
    /*     */   private static String usageWarpOverview;
    /*     */   private static String noAvailable;
    /*     */   private static String noSpawns;
    /*     */
    /*     */   public void load() {
        /*  33 */     File file = new File("plugins/System/Warp/", "messages.yml");
        /*  34 */     if (!file.exists()) {
            /*  35 */       File folder = new File("plugins/System/Warp/");
            /*  36 */       if (!folder.exists()) folder.mkdirs();
            /*     */       try {
                /*  38 */         file.createNewFile();
                /*  39 */         writeDefault(file);
                /*  40 */       } catch (IOException e) {
                /*  41 */         e.printStackTrace();
                /*     */       }
            /*     */     }
        /*     */   }
    /*     */
    /*     */   private void writeDefault(File file) {
        /*  47 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        /*     */
        /*  49 */     configuration.set("General.Prefix", "&8[&3WarpSystem&8]&r");
        /*  50 */     configuration.set("Errors.NoPermission", "%prefix% &cYou do not have permissions for that.");
        /*  51 */     configuration.set("Errors.NoPlayer", "%prefix% &cYou must be a player");
        /*  52 */     configuration.set("Errors.WarpDoesNotExist", "%prefix% &cThe warp &7%name% &cwarp does not exist.");
        /*  53 */     configuration.set("Errors.WarpDoesAlreadyExist", "%prefix% &cThe warp &7%name% &calready exists.");
        /*  54 */     configuration.set("Errors.NoSpawnYet", "%prefix% &cThe spawn has not been set yet.");
        /*  55 */     configuration.set("Errors.NotAvailable", "%prefix% &cThis command is not available.");
        /*  56 */     configuration.set("Errors.NoWarps", "%prefix% &cThere are no warps to show");
        /*     */
        /*  58 */     configuration.set("Setup.WarpCreated", "%prefix% &7A new warp with the name &a%name% &7was created&8.");
        /*  59 */     configuration.set("Setup.WarpRemoved", "%prefix% &7The warp with the name &a%name% &7was removed&8.");
        /*  60 */     configuration.set("Setup.SpawnSet", "%prefix% &7The default spawn has been set!");
        /*     */
        /*  62 */     configuration.set("Warping.WarpOverview", "%prefix% &7There are the following warps available&8: &a%warplist%");
        /*  63 */     configuration.set("Warping.MustWait", "%prefix% &7You still have to &await &7before &aexecuting &7the command again!");
        /*  64 */     configuration.set("Warping.Warped", "%prefix% &7You have been teleported to &a%name%");
        /*     */
        /*  66 */     configuration.set("Usages.SetWarp", "%prefix% &cUsage &7/setwarp <Name>");
        /*  67 */     configuration.set("Usages.DelWarp", "%prefix% &cUsage &7/delwarp <Name>");
        /*  68 */     configuration.set("Usages.Warp", "%prefix% &cUsage &7/warp <Name>");
        /*  69 */     configuration.set("Usages.SetSpawn", "%prefix% &cUsage &7/setSpawn");
        /*  70 */     configuration.set("Usages.WarpOverview", "%prefix% &cUsage &7/warps");
        /*     */
        /*     */     try {
            /*  73 */       configuration.save(file);
            /*  74 */     } catch (IOException e) {
            /*  75 */       e.printStackTrace();
            /*     */     }
        /*     */   }
    /*     */
    /*     */
    /*     */   public void loadMessages() {
        /*  81 */     File file = new File("plugins/System/Warp/", "messages.yml");
        /*  82 */     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        /*     */
        /*  84 */     setPrefix(configuration.getString("General.Prefix"));
        /*     */
        /*  86 */     setNoPermission(configuration.getString("Errors.NoPermission"));
        /*  87 */     setNoPlayer(configuration.getString("Errors.NoPlayer"));
        /*  88 */     setWarpDoesNotExist(configuration.getString("Errors.WarpDoesNotExist"));
        /*  89 */     setWarpAlreadyExists(configuration.getString("Errors.WarpDoesAlreadyExist"));
        /*  90 */     setNoSpawnYet(configuration.getString("Errors.NoSpawnYet"));
        /*  91 */     setNoAvailable(configuration.getString("Errors.NotAvailable"));
        /*  92 */     setNoSpawns(configuration.getString("Errors.NoWarps"));
        /*     */
        /*  94 */     setWarpCreated(configuration.getString("Setup.WarpCreated"));
        /*  95 */     setWarpRemoved(configuration.getString("Setup.WarpRemoved"));
        /*  96 */     setSpawnSet(configuration.getString("Setup.SpawnSet"));
        /*     */
        /*  98 */     setWarpOverview(configuration.getString("Warping.WarpOverview"));
        /*  99 */     setMustWait(configuration.getString("Warping.MustWait"));
        /* 100 */     setWarped(configuration.getString("Warping.Warped"));
        /*     */
        /* 102 */     setUsageRemoveWarp(configuration.getString("Usages.DelWarp"));
        /* 103 */     setUsageWarp(configuration.getString("Usages.Warp"));
        /* 104 */     setUsageSetSpawn(configuration.getString("Usages.SetSpawn"));
        /* 105 */     setUsageSetWarp(configuration.getString("Usages.SetWarp"));
        /* 106 */     setUsageSetWarp(configuration.getString("Usages.WarpOverview"));
        /* 107 */     setUsageWarpOverview(configuration.getString("Usages.WarpOverview"));
        /*     */   }
    /*     */
    /*     */
    /*     */   public static String getNoSpawns() {
        /* 112 */     return noSpawns.replace("%prefix%", getPrefix()).replace("&", "§");
        /*     */   }
    /*     */
    /*     */   public static void setNoSpawns(String noSpawns) {
        /* 116 */     MessageFile.noSpawns = noSpawns;
        /*     */   }
    /*     */
    /*     */   public static String getPrefix() {
        /* 120 */     return prefix.replace("&", "§");
        /*     */   }
    /*     */
    /*     */   public static String getNoAvailable() {
        /* 124 */     return noAvailable.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setNoAvailable(String noAvailable) {
        /* 128 */     MessageFile.noAvailable = noAvailable;
        /*     */   }
    /*     */
    /*     */   public static String getUsageWarpOverview() {
        /* 132 */     return usageWarpOverview.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setUsageWarpOverview(String usageWarpOverview) {
        /* 136 */     MessageFile.usageWarpOverview = usageWarpOverview;
        /*     */   }
    /*     */
    /*     */   public static void setPrefix(String prefix) {
        /* 140 */     MessageFile.prefix = prefix;
        /*     */   }
    /*     */
    /*     */   public static String getNoPermission() {
        /* 144 */     return noPermission.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setNoPermission(String noPermission) {
        /* 148 */     MessageFile.noPermission = noPermission;
        /*     */   }
    /*     */
    /*     */   public static String getNoPlayer() {
        /* 152 */     return noPlayer.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setNoPlayer(String noPlayer) {
        /* 156 */     MessageFile.noPlayer = noPlayer;
        /*     */   }
    /*     */
    /*     */   public static String getWarpDoesNotExist(String typed) {
        /* 160 */     return warpDoesNotExist.replace("&", "§").replace("%name%", typed).replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setWarpDoesNotExist(String warpDoesNotExist) {
        /* 164 */     MessageFile.warpDoesNotExist = warpDoesNotExist;
        /*     */   }
    /*     */
    /*     */   public static String getWarpAlreadyExists(String typed) {
        /* 168 */     return warpAlreadyExists.replace("&", "§").replace("%name%", typed).replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setWarpAlreadyExists(String warpAlreadyExists) {
        /* 172 */     MessageFile.warpAlreadyExists = warpAlreadyExists;
        /*     */   }
    /*     */
    /*     */   public static String getWarpCreated(String typed) {
        /* 176 */     return warpCreated.replace("&", "§").replace("%name%", typed).replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setWarpCreated(String warpCreated) {
        /* 180 */     MessageFile.warpCreated = warpCreated;
        /*     */   }
    /*     */
    /*     */   public static String getWarpRemoved(String typed) {
        /* 184 */     return warpRemoved.replace("&", "§").replace("%name%", typed).replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setWarpRemoved(String warpRemoved) {
        /* 188 */     MessageFile.warpRemoved = warpRemoved;
        /*     */   }
    /*     */
    /*     */   public static String getSpawnSet() {
        /* 192 */     return spawnSet.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setSpawnSet(String spawnSet) {
        /* 196 */     MessageFile.spawnSet = spawnSet;
        /*     */   }
    /*     */
    /*     */   public static String getWarped(String name) {
        /* 200 */     return warped.replace("&", "§").replace("%name%", name).replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setWarped(String warped) {
        /* 204 */     MessageFile.warped = warped;
        /*     */   }
    /*     */
    /*     */   public static String getMustWait() {
        /* 208 */     return mustWait.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setMustWait(String mustWait) {
        /* 212 */     MessageFile.mustWait = mustWait;
        /*     */   }
    /*     */
    /*     */   public static String getWarpOverview() {
        /* 216 */     return warpOverview.replace("&", "§").replace("%warplist%", (new LocationFile()).getWarpString()).replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setWarpOverview(String warpOverview) {
        /* 220 */     MessageFile.warpOverview = warpOverview;
        /*     */   }
    /*     */
    /*     */   public static String getUsageSetSpawn() {
        /* 224 */     return usageSetSpawn.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setUsageSetSpawn(String usageSetSpawn) {
        /* 228 */     MessageFile.usageSetSpawn = usageSetSpawn;
        /*     */   }
    /*     */
    /*     */   public static String getUsageSetWarp() {
        /* 232 */     return usageSetWarp.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setUsageSetWarp(String usageSetWarp) {
        /* 236 */     MessageFile.usageSetWarp = usageSetWarp;
        /*     */   }
    /*     */
    /*     */   public static String getUsageRemoveWarp() {
        /* 240 */     return usageRemoveWarp.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setUsageRemoveWarp(String usageRemoveWarp) {
        /* 244 */     MessageFile.usageRemoveWarp = usageRemoveWarp;
        /*     */   }
    /*     */
    /*     */   public static String getUsageWarp() {
        /* 248 */     return usageWarp.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setUsageWarp(String usageWarp) {
        /* 252 */     MessageFile.usageWarp = usageWarp;
        /*     */   }
    /*     */
    /*     */   public static String getNoSpawnYet() {
        /* 256 */     return noSpawnYet.replace("&", "§").replace("%prefix%", getPrefix());
        /*     */   }
    /*     */
    /*     */   public static void setNoSpawnYet(String noSpawnYet) {
        /* 260 */     MessageFile.noSpawnYet = noSpawnYet;
        /*     */   }

}
