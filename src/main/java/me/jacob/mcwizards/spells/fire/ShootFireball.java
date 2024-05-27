package me.jacob.mcwizards.spells.fire;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.entity.Player;
import org.bukkit.entity.Fireball;
import org.jetbrains.annotations.NotNull;


public class ShootFireball extends Spell {
    public ShootFireball(){
        super(
                // The mana cost
                35,
                // The name of the spell
                "Fireball",
                // The description of the spell
                "Shoots a fireball."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        Fireball fireball;
        fireball = player.launchProjectile(Fireball.class);
        fireball.setVelocity(player.getLocation ().getDirection().multiply(0.5));
        fireball.setYield(2);
    }
}
