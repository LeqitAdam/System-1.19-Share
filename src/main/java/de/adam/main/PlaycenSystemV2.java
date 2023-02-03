package de.adam.main;

import de.adam.commands.CMD_Gamemode;
import de.adam.listener.CommandSendListener;
import de.adam.listener.InventoryClickListener;
import de.adam.listener.RepairListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import de.adam.utils.LocationFile;
import de.adam.utils.MessageFile;
import de.adam.utils.SettingsFile;

import java.util.ArrayList;

public class PlaycenSystemV2 extends JavaPlugin implements Listener {

        @SuppressWarnings("unused")
        private static PlaycenSystemV2 instance;

        public static PlaycenSystemV2 getInstance() {
            return PlaycenSystemV2.instance;
        }

        Scoreboard sb;
        public static String pre = "§c§oSystem§7 » ";
        public static String motd = "§6§lLeqitCraft.de §7* §b§lDein Netzwerk §7* §b1.8.0        §7§lDerzeit sind wir in §c§lWartungen";
        public static String nouse = "§7Diesen §cBefehl §7gibt es nicht!";
        //"Unknown command. Type \"/help\" for help.";
        public static String nocmd = "§7Dieser §cBefehl §7ist auf unserem §aServer §c§lgesperrt!";
        public static String console = "§7Du musst ein Spieler sein, um diesen §c§lBefehl §7zu nutzen!";
        public static String noperm = "§7Diesen §cBefehl §7darfst du nicht nutzen!";
        public static String cmdadminaus = "§7Du bist nun für §aalle §7Spieler §asichtbar!";
        public static String cmdadminan = "§7Du bist nun für §aalle §7Spieler §cunsichtbar!";
        public static String cmdbuildaus = "§7Du kannst nun nicht mehr §cBauen!";
        public static String cmdbuildan = "§7Du kannst nun §aBauen!";
        public static String cmdflyaus = "§7Du hast das §bFliegen §7[§cdeaktiviert§7]";
        public static String cmdflyan = "§7Du hast das §bFliegen §7[§aaktiviert§7]";
        public static String cmdflyp01 = " §7Der Spieler §a";
        public static String cmdfly02 = " §7hat dir §bFly §7weggenommen!";
        public static String cmdfly03 = " §7kann nun nicht mehr §bfliegen!";
        public static String cmdfly04 = " §7hat die §bfly §7gegeben!";
        public static String cmdfly05 = " §7kann nun §bfliegen!";
        public static String cmdgm0 = "§7Dein Spielmodus ist nun §7[§aSURVIVAL§7]";
        public static String cmdgm1 = "§7Dein Spielmodus ist nun §7[§aCREATIVE§7]";
        public static String cmdgm2 = "§7Dein Spielmodus ist nun §7[§aADVENTURE§7]";
        public static String cmdgm3 = "§7Dein Spielmodus ist nun §7[§aSPECTATOR§7]";
        public static String cmdspecaus = "§7Du bist nun §cnicht mehr §7im §cSpec-Mode!";
        public static String cmdspecan = "§7Du bist nun im §cSpec-Mode!";
        public static String cmdvaus = "§7Du bist nun für §aalle §7Spieler §asichtbar!";
        public static String cmdvan = "§7Du bist nun für §aalle normalen §7Spieler §cunsichtbar!";
        public static String onehit = "§7Du hast nun ein [§4§lOP §7- §c§lSchwert§7]";

        public ArrayList<String> bau = new ArrayList<String>();
        public ArrayList<String> vanish = new ArrayList<String>();
        public ArrayList<String> admin = new ArrayList<String>();
        public ArrayList<String> spec = new ArrayList<String>();

        private static PlaycenSystemV2 plugin;

        private static boolean permissions;
        private static boolean permissionsForNonNameChanges;
        private static boolean filters;



        public void onEnable() {
            Bukkit.getConsoleSender().sendMessage("§7§m============§4| §6(§a System §6) §4|§7§m============");
            Bukkit.getConsoleSender().sendMessage("§a");
            Bukkit.getConsoleSender().sendMessage("§aPlugin enabled! Version " + this.getDescription().getVersion());
            Bukkit.getConsoleSender().sendMessage("§aPlugin by Adam and CopyRighted by Adam!");
            Bukkit.getConsoleSender().sendMessage("§a");
            Bukkit.getConsoleSender().sendMessage("§7§m============§4| §6(§a System §6) §4|§7§m============");
            plugin = this;
            instance = this;

            //BanSystem.getInstance().getPlayerManager().getPlayer(UUID).getIPs().clear();

            loadFiles();

            registerCommands();
            resgisterEvents();


            this.getServer().getPluginManager().registerEvents(this, this);
            this.loadListener(Bukkit.getPluginManager());

            PlaycenSystemV2.plugin = this;
            this.getServer().getPluginManager().registerEvents((Listener) new RepairListener(), (Plugin) this);
        }

        public void registerCommands() {
            getCommand("gamemode").setExecutor(new CMD_Gamemode());
        }

        public void resgisterEvents() {
            PluginManager pm = Bukkit.getPluginManager();
            pm.registerEvents(new InventoryClickListener(), this);
            pm.registerEvents(new CommandSendListener(), this);
        }

        public void onDisable() {
        }

        private void loadFiles() {
            /* 28 */     SettingsFile settingsFile = new SettingsFile();
            /* 29 */     settingsFile.load();
            /* 30 */     settingsFile.loadSettings();
            /*    */
            /* 32 */     LocationFile locationFile = new LocationFile();
            /* 33 */     locationFile.load();
            /* 34 */     locationFile.loadWarps();
            /*    */
            /* 36 */     MessageFile messageFile = new MessageFile();
            /* 37 */     messageFile.load();
            /* 38 */     messageFile.loadMessages();
            /*    */   }

        public static PlaycenSystemV2 getPlugin() {
            return plugin;
        }

        private void loadListener(final PluginManager pluginManager) {
        }

        public static boolean usingPermissions() {
            return PlaycenSystemV2.permissions;
        }

        public static boolean usingPermissionsForNonNameChanges() {
            return PlaycenSystemV2.permissionsForNonNameChanges;
        }

        public static boolean usingFilters() {
            return PlaycenSystemV2.filters;
        }

        @EventHandler

        public void onJoin(PlayerJoinEvent e) {

            Player p = e.getPlayer();
            for (String S : vanish) {
                Player all = Bukkit.getPlayer(S);
                if (!p.hasPermission("system.vanish.see")) {
                    if (all != null) {
                        p.hidePlayer(all);
                    } else {
                        vanish.remove(S);
                    }
                } else {
                    p.showPlayer(all);
                }
            }
            for (String S : admin) {
                Player all = Bukkit.getPlayer(S);
                if (!(p.getName().equals("LeqitSweden") || p.getName().equals("NeraxHD"))) {
                    if (all != null) {
                        p.hidePlayer(all);
                    } else {
                        admin.remove(S);
                    }
                } else {
                    p.showPlayer(all);
                }
            }
            for (String S : spec) {
                Player all = Bukkit.getPlayer(S);
                if (!p.hasPermission("system.spec.see")) {
                    if (all != null) {
                        p.hidePlayer(all);
                    } else {
                        spec.remove(S);
                    }
                } else {
                    p.showPlayer(all);
                }
            }


        }
}
