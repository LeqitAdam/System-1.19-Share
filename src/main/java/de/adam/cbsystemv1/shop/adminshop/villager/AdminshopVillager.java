package de.adam.cbsystemv1.shop.adminshop.villager;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

public class AdminshopVillager {
    public AdminshopVillager(Location loc) {
        String villagername = VillagerHandler.getAdminshopname();
        villagername = ChatColor.translateAlternateColorCodes('&', villagername);
        // Create the villager entity
        Villager villager = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        // Set the villager's attributes
        villager.setAI(false);
        villager.setCustomName(villagername);
        villager.setCustomNameVisible(true);
        villager.setSilent(true);
        villager.setCollidable(false);
        villager.setGravity(false);
        villager.setCanPickupItems(false);
        villager.setInvulnerable(true);
        villager.setRemoveWhenFarAway(false);
        villager.setPersistent(true);
        villager.addScoreboardTag("immobile");
        villager.setProfession(Villager.Profession.CARTOGRAPHER);
    }
    public static Villager getVillager(String villagername) {
        for (Entity entity : Bukkit.getWorld("world").getEntities()) {
            if (entity instanceof Villager && entity.getCustomName() != null && entity.getCustomName().equalsIgnoreCase(villagername)) {
                return (Villager) entity;
            }
        }
        return null; // Return null if no matching Villager is found
    }
}
