package de.adam.cbsystemv1.files;

public class Messages {
    public static String
            notenoughbalance = "§cDu hast nicht genug Geld.",
            unknownmaterial = "§cDieses Material ist unbekannt.",
            notenoughinvspace = "§cDu hast nich genug Platz im inventar.",
    //SignCommand
            signcommandusage = "§cBitte benutze: §7/sign <Nachricht>",
            signcommanddelusage = "§cBitte benutze: §7/sign del",
            signremoved = "§7Du hast §aerfolgreich §7die §cSignatur entfernt!",
            signadded = "§7Item §aerfolgreich §csigniert!",
            signedalready = "§7Das §aItem §7wurde breits §csigniert!",
            signonlyone = "§7Du darfst nur ein §aItem §7gleichzeitig §csignieren!",
            signholditeminhand = "§7Du §cmusst §7ein §aItem §7in der Hand halten!",
            signitemnotsigned = "§7Das §aItem §7wurde noch §cnicht signiert!",
    //SetspawnCommand
            setspawncommandusage = "§cBitte benutze: §7/setspawn",
            setspawnsucces = "§7Der Spawn wurde §agesetzt!",
    //ClearlagCommand
            clearlagcommandusage = "§cBitte benutze: §7/clearlag",
    //CutomItemCommand
            customitemcommandusage = "§cBitte benutze: §7/customitem create <item> <amount>",
            customitemcreated = "§bDas Item wurde in dein Inventar gelegt.",
    //RangErhalten
            hasrankalready = "§cDu hast bereits einen besseren Rang.",
            zockerrankadded = "§7Du hast den §dZocker§7-§aRang §7erhalten.",
    //Setvillager
            setvillagercommandusage = "§7Nutze /setvillager <admin, shop>",
    //WarpCommand
            warpcommandusage = "§cBitte benutze: §7/warp <warp>",
            warpcommandteleported1 = "§7Du wurdest zum §aWarp§7: §b",
            warpcommandteleported2 = " §7teleportiert.",
            warpnotfound = "§cDieser Warp wurde nicht gefunden.",
    //RandCommand
            randcommandusage = "",
            randcommandnoperms = "§cDu darfst diesen Rand nicht verwenden.",
    //EcCommand
            ecuseuuid = "§cDieser Spieler ist nicht online, benutze bitte die UUID.",
    //WerkbankCommand
            wbcommandusage = "§cBitte benutze: §7/wb";
}
