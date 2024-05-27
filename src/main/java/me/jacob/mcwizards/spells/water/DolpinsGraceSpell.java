package me.jacob.mcwizards.spells.water;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class DolpinsGraceSpell extends Spell {
    public DolpinsGraceSpell() {
        super(
            // The mana cost
            20,
            // The name of the spell
            "Dolphins grace",
            // The description of the spell
            "Gives you dolphins grace."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 300, 1));
    }
}
