package de.adam.main;

//import ch.dkrieger.bansystem.lib.BanSystem;
import de.adam.commands.*;
import de.adam.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;

public class ZockerWorldCBV1 extends JavaPlugin implements Listener {

    //"Unknown command. Type \"/help\" for help.";

    @SuppressWarnings("unused")
    private static ZockerWorldCBV1 instance;
    public static ZockerWorldCBV1 getInstance() {
        return ZockerWorldCBV1.instance;
    }
    Scoreboard sb;
    public static String prefix = "§c§oSystem§7 » ";
    public ArrayList<String> admin = new ArrayList<String>();
    public ArrayList<String> spec = new ArrayList<String>();
    private static ZockerWorldCBV1 plugin;

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

        registerCommands();
        resgisterEvents();


        this.getServer().getPluginManager().registerEvents(this, this);
        this.loadListener(Bukkit.getPluginManager());

        ZockerWorldCBV1.plugin = this;
        this.getServer().getPluginManager().registerEvents((Listener) new RepairListener(), (Plugin) this);

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
    public void registerCommands() {
        getCommand("setspawn").setExecutor(new SetspawnCommand());
        getCommand("clearlag").setExecutor(new ClearlagCommand());
        getCommand("werkbank").setExecutor(new WerkbankCommand());
        getCommand("sign").setExecutor(new SignCommand(plugin));
    }

    public void resgisterEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new CommandSendListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new LeaveListener(), this);
    }

    //Clearlag Methods
    private void startClearLag(){
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            ClearlagCommand cl = new ClearlagCommand();
            cl.clearlag();
            anounceClearLag();
        }, 20*60*15, 20*60*15);
    }
    private int time;
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

    private void loadListener(final PluginManager pluginManager) {
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
