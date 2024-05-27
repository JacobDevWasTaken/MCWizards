package me.jacob.mcwizards.listeners;

import me.jacob.mcwizards.wandengine.ClickEngine;
import me.jacob.mcwizards.wandengine.Wand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class ClickListener implements Listener {
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (isHoldingWand(event.getPlayer())) {
            ClickEngine.handleClick(event.getPlayer(), event.getAction().isRightClick());
        }
    }

    public static boolean isHoldingWand(@NotNull Player player) {
        return Wand.isWand(player.getInventory().getItemInMainHand()) || Wand.isWand(player.getInventory().getItemInOffHand());
    }
}
