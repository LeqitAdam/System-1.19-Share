package de.adam.commands;

import de.adam.main.PlaycenSystemV2;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class CMD_Clearlag implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("system.clearlag") || p.hasPermission("system.*")){
                if(args.length == 0){
                    clearlag();
                    return true;
                } else
                    p.sendMessage(PlaycenSystemV2.pre + " §cBitte benutze: §7/clearlag");
            } else
                p.sendMessage(PlaycenSystemV2.pre + PlaycenSystemV2.noperm);
        } else
            sender.sendMessage(PlaycenSystemV2.pre + " §cNur Spieler können diesen Befehl nutzen!");
        clearlag();
        return false;
    }

    public void clearlag(){
        int itemcount = 0;
        for(World w : Bukkit.getWorlds()){
            for(Entity e : w.getEntities()){
                if(e.getType() == EntityType.DROPPED_ITEM){
                    itemcount += ((Item) e).getItemStack().getAmount();
                    e.remove();
                }
            }
        }
        for(Player all : Bukkit.getOnlinePlayers()){
            if(itemcount == 1){
                all.sendMessage(PlaycenSystemV2.pre + " §7Es wurde §c" + itemcount + " §7Item entfernt");
            }
            if(itemcount != 1) {
                all.sendMessage(PlaycenSystemV2.pre + " §7Es wurden §c" + itemcount + " §7Items entfernt");
            }
        }
    }
}
