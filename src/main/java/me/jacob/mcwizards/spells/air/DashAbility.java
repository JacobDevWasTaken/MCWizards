package me.jacob.mcwizards.spells.air;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class DashAbility extends Spell {
    public DashAbility() {
        super(
            // The mana cost
            10,
            // The name of the spell
            "Dash",
            // The description of the spell
            "Launches you forwards."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        @NotNull Vector velocity;
        velocity = player.getLocation().getDirection();
        velocity = velocity.setY(0.01);
        velocity = velocity.normalize();
        velocity = velocity.multiply(1.0);
        velocity = velocity.setY(0.8);
        velocity = velocity.add(player.getVelocity());
        player.setVelocity(velocity);
    }
}
