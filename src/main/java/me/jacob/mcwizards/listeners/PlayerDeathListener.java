package me.jacob.mcwizards.listeners;

import me.jacob.mcwizards.wandengine.Wand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (!event.getDrops().isEmpty()) {
            event.getDrops().removeIf(Wand::isWand);
        }
    }
}
