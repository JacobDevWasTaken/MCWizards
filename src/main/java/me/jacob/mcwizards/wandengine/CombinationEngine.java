package me.jacob.mcwizards.wandengine;

import me.jacob.mcwizards.spellengine.Spell;

import java.util.HashMap;
import java.util.List;

public class CombinationEngine {
    final HashMap<List<Boolean>, Spell> abilityCombinationMap;

    public CombinationEngine() {
        abilityCombinationMap = new HashMap<>();
    }

    public void registerCombination(List<Boolean> combination, Spell spell) {
        abilityCombinationMap.put(combination, spell);
    }

    public Spell getSpellFromCombination(List<Boolean> key) {
        return abilityCombinationMap.get(key);
    }
}
