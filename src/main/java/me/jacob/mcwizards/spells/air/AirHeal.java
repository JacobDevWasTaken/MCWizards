package me.jacob.mcwizards.spells.air;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AirHeal extends Spell {
    public AirHeal() {
        super(
            // The mana cost
            20,
            // The name of the spell
            "Air heal",
            // The description of the spell
            "Heals you 2 hearts."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        if (player.getHealth() + 4 > 20) {
            player.setHealth(20);
        } else {
            player.setHealth(player.getHealth() + 4);
        }
    }
}
