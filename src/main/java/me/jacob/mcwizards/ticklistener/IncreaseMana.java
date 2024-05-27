package me.jacob.mcwizards.ticklistener;

import me.jacob.mcwizards.spellengine.ManaEngine;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class IncreaseMana extends BukkitRunnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (ManaEngine.getMana(player) < 100){
                ManaEngine.setMana(player, ManaEngine.getMana(player) + 1);
            } else if (ManaEngine.getMana(player) > 100)  {
                ManaEngine.setMana(player, 100);
            }

            @NotNull TextComponent mana = Component.text(ManaEngine.getMana(player) + "§r✎ Mana").color(NamedTextColor.BLUE);

            player.sendActionBar(mana);
        }
    }
}
