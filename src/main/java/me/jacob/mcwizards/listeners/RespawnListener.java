package me.jacob.mcwizards.listeners;

import me.jacob.mcwizards.wandengine.Wand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.getPlayer().getInventory().addItem(Wand.getWandItem());
    }
}
