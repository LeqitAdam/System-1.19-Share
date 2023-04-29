package de.adam.cbsystemv1.commands;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class ClearlagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission(Permissions.clearlagcommand)){
                if(args.length == 0){
                    clearLag();
                    return true;
                } else p.sendMessage(ZockerWorldCBV1.prefix + Messages.clearlagcommandusage);
            } else p.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
        } else sender.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.onlyplayeruse);
        clearLag();
        return false;
    }
    public void clearlag1(){
        int itemcount = 0;
        for(World w : Bukkit.getServer().getWorlds()){
            for(Entity e : w.getEntities()){
                if(e != null) {
                    if(e.getType() == EntityType.DROPPED_ITEM){
                        itemcount += ((Item) e).getItemStack().getAmount();
                        e.remove();
                    }
                }else Bukkit.broadcastMessage(ZockerWorldCBV1.prefix + "§7Es wurden §c0 §7Items entfernt.");
            }
        }
        for(Player all : Bukkit.getOnlinePlayers()){
            if(itemcount == 1){
                all.sendMessage(ZockerWorldCBV1.prefix + " §7Es wurde §c" + itemcount + " §7Item entfernt");
            }
            if(itemcount != 1) {
                all.sendMessage(ZockerWorldCBV1.prefix + " §7Es wurden §c" + itemcount + " §7Items entfernt");
            }
        }
    }
    public void clearLag() {
        int itemCount = 0;
        int mobCount = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    itemCount += ((Item) entity).getItemStack().getAmount();
                    entity.remove();
                } else if (entity instanceof Mob && !entity.isCustomNameVisible()) {
                    mobCount++;
                    entity.remove();
                }
            }
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (itemCount == 1) {
                player.sendMessage(ZockerWorldCBV1.prefix + " §7Es wurde §c" + itemCount + " §7Item entfernt");
            } else {
                player.sendMessage(ZockerWorldCBV1.prefix + " §7Es wurden §c" + itemCount + " §7Items entfernt");
            }
            if (mobCount == 1) {
                player.sendMessage(ZockerWorldCBV1.prefix + " §7Es wurde §c" + mobCount + " §7Mob entfernt");
            } else if (mobCount > 1) {
                player.sendMessage(ZockerWorldCBV1.prefix + " §7Es wurden §c" + mobCount + " §7Mobs entfernt");
            }
        }
    }

}
