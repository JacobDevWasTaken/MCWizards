package me.jacob.mcwizards.spells.air;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class LevitationSpell extends Spell {
    public LevitationSpell() {
        super(
            // The mana cost
            15,
            // The name of the spell
            "Levitation",
            // The description of the spell
            "Makes you levitate."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 20, 3));
    }
}
