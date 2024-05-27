package me.jacob.mcwizards.ui;

import me.jacob.mcwizards.RegisterSpells;
import me.jacob.mcwizards.commands.LBindCommand;
import me.jacob.mcwizards.commands.RBindCommand;
import me.jacob.mcwizards.spellengine.ManaEngine;
import me.jacob.mcwizards.spellengine.Spell;
import me.jacob.mcwizards.wandengine.ClickEngine;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;

public class SpellCombinationUI {
    public static Component buildText(@NotNull List<Boolean> combination) {
        Component text = Component.text("");

        for (Boolean bool : combination.subList(0, combination.size() - 1)) {
            text = text.append(Component.text(bool ? "R" : "L")
                    .color(bool ? NamedTextColor.YELLOW : NamedTextColor.BLUE));
            text = text.append(Component.text(" + "));
        }

        text = text.append(Component.text(combination.getLast() ? "R" : "L")
                .color(combination.getLast() ? NamedTextColor.YELLOW : NamedTextColor.BLUE));

        if (combination.size() < 4) {
            text = text.append(Component.text(" + "));
            text = text.append(Component.text("_").color(NamedTextColor.LIGHT_PURPLE));
        }

        return text;
    }

    public static Component buildSubtitle(@NotNull List<Boolean> combination, @NotNull Player player) {
        Component text = Component.text("");

        if (RegisterSpells.hasSpell(combination) || combination.size() == 1) {
            Spell spell;
            if (ClickEngine.getCombination(player).size() == 1) {
                if (ClickEngine.getCombination(player).getFirst()) {
                    spell = RegisterSpells.getSpell(RBindCommand.getBindCombination(player));
                } else {
                    spell = RegisterSpells.getSpell(LBindCommand.getBindCombination(player));
                }
            } else {
                spell = RegisterSpells.getSpell(combination);
            }

            text = text.append(Component.text(spell.name).color(NamedTextColor.DARK_PURPLE));

            text = text.append(Component.text(" | "));

            if (ManaEngine.getMana(player) >= spell.manaCost) {
                text = text.append(Component.text("[")
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.GREEN));

                text = text.append(Component.keybind("key.sneak")
                        .decoration(TextDecoration.ITALIC, true)
                        .color(NamedTextColor.GREEN));

                text = text.append(Component.text("] Cast | ")
                        .decoration(TextDecoration.ITALIC, false)
                        .color(NamedTextColor.GREEN));
            } else {
                text = text.append(Component.text("[Not enough mana]").color(NamedTextColor.RED));
            }

            text = text.append(Component.text(" | "));

            text = text.append(Component.text("[")
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.GREEN));

            text = text.append(Component.keybind("key.drop")
                    .decoration(TextDecoration.ITALIC, true)
                    .color(NamedTextColor.GREEN));

            text = text.append(Component.text("] Info")
                    .decoration(TextDecoration.ITALIC, false)
                    .color(NamedTextColor.GREEN));
        } else {
            text = text.append(Component.text("[No such spell]").color(NamedTextColor.RED));
        }

        return text;
    }

    public static void showCombination(@NotNull Player player, @NotNull List<Boolean> combination) {
        final Title.Times times = Title.Times.times(Duration.ofMillis(0), Duration.ofMillis(1000), Duration.ofMillis(200));
        final Title title = Title.title(buildText(combination), buildSubtitle(combination, player), times);

        player.showTitle(title);
    }
}
