package de.adam.cbsystemv1.extras;

import de.adam.cbsystemv1.main.ZockerWorldCBV1;
import org.bukkit.entity.Player;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class AnvilTask extends BukkitRunnable {

    private static HashMap<AnvilInventory, AnvilTask> anvilTasks;
    private AnvilInventory inv;
    private Player player;

    static {
        AnvilTask.anvilTasks = new HashMap<AnvilInventory, AnvilTask>();
    }

    public AnvilTask(final AnvilInventory inv, final Player player) {
        this.inv = inv;
        this.player = player;
        AnvilTask.anvilTasks.put(inv, this);
        this.runTaskTimer((Plugin) ZockerWorldCBV1.getPlugin(), 1L, 3L);
    }

    public void run() {
        if (this.inv.getViewers().size() == 0) {
            this.cancel();
        }
        ColorHandler.getTranslatedItem(this.player, this.inv, this);
    }

    public static AnvilTask getTask(final AnvilInventory inv) {
        return AnvilTask.anvilTasks.get(inv);
    }
}
