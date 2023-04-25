package de.adam.cbsystemv1.commands;

import de.adam.cbsystemv1.files.Messages;
import de.adam.cbsystemv1.files.Permissions;
import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class ClearlagCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission(Permissions.clearlagcommand)){
                if(args.length == 0){
                    clearlag();
                    return true;
                } else p.sendMessage(ZockerWorldCBV1.prefix + Messages.clearlagcommandusage);
            } else p.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.noperm);
        } else sender.sendMessage(ZockerWorldCBV1.prefix + de.adam.globalsystemv1.files.Messages.onlyplayeruse);
        clearlag();
        return false;
    }
    public void clearlag(){
        int itemcount = 0;
        for(World w : Bukkit.getServer().getWorlds()){
            for(Entity e : w.getEntities()){
                if(e != null) {
                    if(e.getType() == EntityType.DROPPED_ITEM || e.getType().getName() == null){
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
}
