package me.jacob.mcwizards.spells.water;

import me.jacob.mcwizards.spellengine.ManaEngine;
import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class RiptideAbility extends Spell {
    public RiptideAbility() {
        super(
            // The mana cost
            30,
            // The name of the spell
            "Riptide",
            // The description of the spell
            "Launches you forwards like a riptide trident (only works in water)."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        if (player.isInWater() || !player.getWorld().isClearWeather()) {
            Vector velocity = player.getLocation().getDirection().multiply(3.5);
            velocity = velocity.add(player.getVelocity());
            player.setVelocity(velocity);
        } else {
            player.sendMessage("You are not in water!");
            ManaEngine.setMana(player, ManaEngine.getMana(player) + 30);
        }
    }
}
