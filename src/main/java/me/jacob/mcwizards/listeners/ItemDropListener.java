package me.jacob.mcwizards.listeners;

import me.jacob.mcwizards.ui.SpellBookUI;
import me.jacob.mcwizards.wandengine.ClickEngine;
import me.jacob.mcwizards.wandengine.Wand;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import static me.jacob.mcwizards.wandengine.ClickEngine.getLastClickTimestamp;
import static me.jacob.mcwizards.wandengine.ClickEngine.removeCombination;

public class ItemDropListener implements Listener {
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (getLastClickTimestamp(event.getPlayer()) + 1100 < System.currentTimeMillis()) {
            removeCombination(event.getPlayer());
        }

        if (event.getItemDrop().getItemStack().getType() == Material.BLAZE_ROD) {
            if (Wand.isWand(event.getItemDrop().getItemStack())) {
                if (ClickEngine.getCombination(event.getPlayer()).isEmpty()) {
                    SpellBookUI.openBook(event.getPlayer());
                } else {
                    if (ClickEngine.getCombination(event.getPlayer()).size() > 1) {
                        SpellBookUI.showSpellManual(event.getPlayer());
                    }
                }
                event.setCancelled(true);
            }
        }
    }
}
