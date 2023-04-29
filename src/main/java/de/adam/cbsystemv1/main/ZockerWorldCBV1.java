package de.adam.cbsystemv1.main;

import de.adam.cbsystemv1.commands.*;
import de.adam.cbsystemv1.listener.*;
import de.adam.cbsystemv1.methods.EconManager;
import de.adam.cbsystemv1.shop.adminshop.villager.VillagerHandler;
import de.adam.globalsystemv1.listener.TabCompleteHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ZockerWorldCBV1 extends JavaPlugin implements Listener {

    //"Unknown command. Type \"/help\" for help.";
    @SuppressWarnings("unused")
    private static ZockerWorldCBV1 instance;
    public static ZockerWorldCBV1 getInstance() {
        return ZockerWorldCBV1.instance;
    }
    Scoreboard sb;
    public static String prefix = "§9CityBuild §7» ";
    public ArrayList<String> admin = new ArrayList<String>();
    public ArrayList<String> spec = new ArrayList<String>();
    private static ZockerWorldCBV1 plugin;
    private static final Logger log = Logger.getLogger("Minecraft");

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§7§m============§4| §6(§a System §6) §4|§7§m============");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§aPlugin enabled! Version " + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§aPlugin by Adam and CopyRighted by Adam!");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§7§m============§4| §6(§a System §6) §4|§7§m============");
        plugin = this;
        instance = this;

        registerCommands();
        resgisterEvents();
        registerTabCompletes();
        registerEcon();

        this.getServer().getPluginManager().registerEvents(this, this);

        ZockerWorldCBV1.plugin = this;
        this.getServer().getPluginManager().registerEvents(new RepairHandler(), this);
        //clearlag
        startClearLag();
    }
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7§m============§4| §6(§a System §6) §4|§7§m============");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§aPlugin disabled! Version " + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§aPlugin by Adam and CopyRighted by Adam!");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§7§m============§4| §6(§a System §6) §4|§7§m============");
    }
    private void registerCommands() {
        getCommand("setspawn").setExecutor(new SetspawnCommand());
        getCommand("clearlag").setExecutor(new ClearlagCommand());
        getCommand("werkbank").setExecutor(new WerkbankCommand());
        getCommand("sign").setExecutor(new SignCommand(plugin));
        getCommand("customitem").setExecutor(new CustomItemCommand());
        getCommand("setshop").setExecutor(new SetshopCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("rand").setExecutor(new RandCommand());
    }
    private void registerTabCompletes() {
        getCommand("customitem").setTabCompleter(new TabCompleteHandler());
    }
    private void resgisterEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClickHandler(), this);
        pm.registerEvents(new CommandHandler(), this);
        pm.registerEvents(new PlayerHandler(), this);
        pm.registerEvents(new RepairHandler(), this);
        pm.registerEvents(new CustomItemHandler(), this);
        pm.registerEvents(new VillagerHandler(), this);
        pm.registerEvents(new InventoryHandler(), this);
        pm.registerEvents(new de.adam.cbsystemv1.shop.adminshop.listener.InventoryHandler(), this);
    }
    public void registerEcon() {
        EconManager econ = new EconManager();
        if (!econ.setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        econ.setupChat();
        econ.setupPermissions();
    }
    //Clearlag Methods
    private void startClearLag(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                ClearlagCommand clear = new ClearlagCommand();
                clear.clearLag();
            }
        }, 0L, 20L * 60L * 15L); // 20 ticks per second * 60 seconds * 15 minutes
        anounceClearLag();
    }
    private void anounceClearLag(){
        Bukkit.getScheduler().runTaskLater(this, () -> {
            for(Player all : Bukkit.getOnlinePlayers()){
                all.sendMessage(ZockerWorldCBV1.prefix + " §aIn 1 Minute werden alle am Boden liegenden Items gelöscht.");
            }
        }, 20*60*14);

    }
    public static ZockerWorldCBV1 getPlugin() {
        return plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
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
