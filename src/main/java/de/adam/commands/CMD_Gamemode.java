package de.adam.commands;

import de.adam.main.PlaycenSystemV2;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Gamemode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("system.gamemode")) {
            if (cmd.getName().equalsIgnoreCase("gamemode") || cmd.getName().equalsIgnoreCase("gm")) {
                if (args.length == 1) {
                    if(sender instanceof Player) {
                        Player p = (Player) sender;
                        changeOwnGamemode(p, args);
                    }else
                        sender.sendMessage(PlaycenSystemV2.pre + " §cNur Spieler können Ihren eigenen Gamemode ändern!");
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        if (!target.hasPermission("system.gamemode.others.bypass.ignore")) {
                            if (sender.hasPermission("system.gamemode.others")) {
                                changeOtherGamemode(sender, target, args);
                            } else sender.sendMessage(PlaycenSystemV2.pre + PlaycenSystemV2.noperm);
                        } else if (!(target.getName().equals("LeqitSweden") || target.getName().equals("NeraxHD")
                        || target.getUniqueId().equals("6c0553a11f8b49979bf33588a5459f36"))) {
                            if (sender.hasPermission("system.gamemode.others")) {
                                changeOtherGamemode(sender, target, args);
                            } else sender.sendMessage(PlaycenSystemV2.pre + PlaycenSystemV2.noperm);
                        } else if (sender.getName().equals("LeqitSweden") || sender.getName().equals("LeqitAdam")) {
                            changeOtherGamemode(sender, target, args);
                        } else
                            sender.sendMessage(PlaycenSystemV2.pre + " §7Du darfst den Spielmodus von §a" + args[1] + " §cnicht verändern!");
                    } else sender.sendMessage(PlaycenSystemV2.pre + " §7Der Spieler §a" + args[1] + " §7ist §cnicht online!");
                } else sender.sendMessage(PlaycenSystemV2.pre + " §cBitte benutze: §7/gm <0, 1, 2, 3> <Spieler>");
            } else sender.sendMessage(PlaycenSystemV2.pre + " §cBitte benutze: §7/gm <0, 1, 2, 3> <Spieler>");
        } else sender.sendMessage(PlaycenSystemV2.pre + PlaycenSystemV2.noperm);
        return false;
    }

    public static boolean changeOwnGamemode(Player p, String[] args) {
        String arg = args[0];
        int i = Integer.parseInt(arg);

        switch (i) {
            case 3:
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                return false;
            case 2:
                p.setGameMode(GameMode.ADVENTURE);
                p.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                return false;
            case 1:
                p.setGameMode(GameMode.CREATIVE);
                p.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                return false;
            case 0:
                p.setGameMode(GameMode.SURVIVAL);
                p.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                return false;
            default:
                p.sendMessage(PlaycenSystemV2.pre + " §cUnbekannter Spielmodus");
                return false;
        }
    }

    public static boolean changeOtherGamemode(CommandSender sender, Player target, String[] args) {
        String arg = args[0];
        int i = Integer.parseInt(arg);

        switch (i) {
            case 3:
                target.setGameMode(GameMode.SPECTATOR);
                target.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aSPECTATOR§7]");
                sender.sendMessage(PlaycenSystemV2.pre + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSpectator §7Modus!");
                return false;
            case 2:
                target.setGameMode(GameMode.ADVENTURE);
                target.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aADVENTURE§7]");
                sender.sendMessage(PlaycenSystemV2.pre + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cAbenteuer §7Modus!");
                return false;
            case 1:
                target.setGameMode(GameMode.CREATIVE);
                target.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aCREATIVE§7]");
                sender.sendMessage(PlaycenSystemV2.pre + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cKreativ §7Modus!");
                return false;
            case 0:
                target.setGameMode(GameMode.SURVIVAL);
                target.sendMessage(PlaycenSystemV2.pre + " §7Dein Spielmodus ist nun §7[§aSURVIVAL§7]");
                sender.sendMessage(PlaycenSystemV2.pre + " §7Der Spieler §a" + target.getName() + " §7ist nun im §cSurvival §7Modus!");
                return false;
            default:
                sender.sendMessage(PlaycenSystemV2.pre + " §cUnbekannter Spielmodus");
                return false;
        }
    }

}
