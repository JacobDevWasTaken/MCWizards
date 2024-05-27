package me.jacob.mcwizards;

import me.jacob.mcwizards.spellengine.Spell;
import me.jacob.mcwizards.spells.air.AirHeal;
import me.jacob.mcwizards.spells.air.DashAbility;
import me.jacob.mcwizards.spells.air.LevitationSpell;
import me.jacob.mcwizards.spells.air.ThunderBolt;
import me.jacob.mcwizards.spells.earth.Boulder;
import me.jacob.mcwizards.spells.earth.EarthFangs;
import me.jacob.mcwizards.spells.earth.EarthFangsTwo;
import me.jacob.mcwizards.spells.fire.FireCircle;
import me.jacob.mcwizards.spells.fire.FireRain;
import me.jacob.mcwizards.spells.fire.ShootFireball;
import me.jacob.mcwizards.spells.water.DolpinsGraceSpell;
import me.jacob.mcwizards.spells.water.RiptideAbility;
import me.jacob.mcwizards.spells.water.WaterHeal;
import me.jacob.mcwizards.wandengine.CombinationEngine;

import java.util.List;

public class RegisterSpells {
    public static final CombinationEngine combinations = new CombinationEngine();

    public static void register() {


        // Air

        combinations.registerCombination(List.of(true, true), new DashAbility());

        combinations.registerCombination(List.of(true, true, false), new AirHeal());
        combinations.registerCombination(List.of(true, true, true), new LevitationSpell());

        combinations.registerCombination(List.of(true, true, false, false), new ThunderBolt());
        combinations.registerCombination(List.of(true, true, false, true), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(true, true, true, false), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(true, true, true, true), new Spell(0, "Spell", "A spell"));



        // Earth

        combinations.registerCombination(List.of(true, false), new Boulder());

        combinations.registerCombination(List.of(true, false, false), new EarthFangs());
        combinations.registerCombination(List.of(true, false, true), new EarthFangsTwo());

        combinations.registerCombination(List.of(true, false, false, false), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(true, false, false, true), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(true, false, true, false), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(true, false, true, true), new Spell(0, "Spell", "A spell"));



        // Fire

        combinations.registerCombination(List.of(false, true), new ShootFireball());

        combinations.registerCombination(List.of(false, true, false), new FireCircle());
        combinations.registerCombination(List.of(false, true, true), new FireRain());

        combinations.registerCombination(List.of(false, true, false, false), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(false, true, false, true), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(false, true, true, false), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(false, true, true, true), new Spell(0, "Spell", "A spell"));



        // Water

        combinations.registerCombination(List.of(false, false), new DolpinsGraceSpell());

        combinations.registerCombination(List.of(false, false, false), new WaterHeal());
        combinations.registerCombination(List.of(false, false, true), new RiptideAbility());

        combinations.registerCombination(List.of(false, false, false, false), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(false, false, false, true), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(false, false, true, false), new Spell(0, "Spell", "A spell"));
        combinations.registerCombination(List.of(false, false, true, true), new Spell(0, "Spell", "A spell"));
    }

    public static Spell getSpell(List<Boolean> combination) {
        return combinations.getSpellFromCombination(combination);
    }

    public static boolean hasSpell(List<Boolean> combination) {
        return getSpell(combination) != null;
    }
}
