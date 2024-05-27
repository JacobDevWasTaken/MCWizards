package me.jacob.mcwizards.spellengine;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Spell {
    public int manaCost;
    public String name;
    public String description;

    public Spell(int manaCost, @NotNull String name, @NotNull String description) {
        builder(manaCost, name, description);
    }

    public void builder(int manaCost, String name, String description) {
        this.name = name;
        this.manaCost = manaCost;
        this.description = description;
    }

    public String getManaCost() {
        return String.valueOf(manaCost);
    }

    public void cast(@NotNull Player player) {
        // Override this method

        player.sendMessage("This spell doesn't exist yet");
    }
}
