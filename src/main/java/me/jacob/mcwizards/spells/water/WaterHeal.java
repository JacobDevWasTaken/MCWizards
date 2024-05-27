package me.jacob.mcwizards.spells.water;

import me.jacob.mcwizards.spellengine.ManaEngine;
import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class WaterHeal extends Spell {
    public WaterHeal() {
        super(
            // The mana cost
            20,
            // The name of the spell
            "Water Heal",
            // The description of the spell
            "Gives regeneration for 20 seconds (only works in water)."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        if (player.isInWater() || !player.getWorld().isClearWeather()) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 400, 0));
        } else {
            player.sendMessage("You are not in water!");
            ManaEngine.setMana(player, ManaEngine.getMana(player) + 20);
        }
    }
}
