package me.jacob.mcwizards.listeners;

import me.jacob.mcwizards.wandengine.ClickEngine;
import me.jacob.mcwizards.wandengine.Wand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import static me.jacob.mcwizards.wandengine.ClickEngine.getLastClickTimestamp;
import static me.jacob.mcwizards.wandengine.ClickEngine.removeCombination;

public class PlayerSneakListener implements Listener {
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (getLastClickTimestamp(player) + 1100 < System.currentTimeMillis()) {
            removeCombination(player);
        }

        if (event.isSneaking()) {
            if (ClickListener.isHoldingWand(player) && !ClickEngine.getCombination(player).isEmpty()) {
                Wand.tryCastCombination(player);
            }
        }
    }
}
