package me.jacob.mcwizards.wandengine;

import me.jacob.mcwizards.MCWizards;
import me.jacob.mcwizards.RegisterSpells;
import me.jacob.mcwizards.commands.LBindCommand;
import me.jacob.mcwizards.commands.RBindCommand;
import me.jacob.mcwizards.spellengine.ManaEngine;
import me.jacob.mcwizards.spellengine.Spell;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static me.jacob.mcwizards.wandengine.ClickEngine.removeCombination;

public class Wand {
    public static boolean isWand(ItemStack item) {
        if (item == null) {
            return false;
        }
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "isWand");
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }
        PersistentDataContainer PDC = meta.getPersistentDataContainer();
        return PDC.has(key, PersistentDataType.BOOLEAN);
    }

    public static void castCombination(@NotNull Player player, List<Boolean> combination) {
        RegisterSpells.getSpell(combination).cast(player);

        Sound sound = Sound.sound(Key.key("minecraft:entity.experience_orb.pickup"), Sound.Source.MUSIC, 1f, 1f);
        player.playSound(sound);

        removeCombination(player);
        player.clearTitle();
    }

    public static void tryCastCombination(@NotNull Player player) {
        if (!player.hasCooldown(Material.BLAZE_ROD)) {
            if (!ClickEngine.getCombination(player).isEmpty()) {

                if (ClickEngine.getCombination(player).size() == 1) {
                    if (ClickEngine.getCombination(player).getFirst()) {
                        ClickEngine.setCombination(player, RBindCommand.getBindCombination(player));
                    } else {
                        ClickEngine.setCombination(player, LBindCommand.getBindCombination(player));
                    }
                }

                @NotNull Spell spell = RegisterSpells.getSpell(ClickEngine.getCombination(player));

                if (ManaEngine.getMana(player) >= spell.manaCost) {
                    castCombination(player, ClickEngine.getCombination(player));
                    player.setCooldown(Material.BLAZE_ROD, 10);
                    ManaEngine.setMana(player, ManaEngine.getMana(player) - spell.manaCost);
                }
            }
        }
    }

    public static ItemStack getWandItem() {
        ItemStack wand = new ItemStack(Material.BLAZE_ROD);

        ItemMeta meta = wand.getItemMeta();
        meta.displayName(Component.text("WAND")
                .color(TextColor.fromHexString("#8f007b"))
                .decoration(TextDecoration.BOLD, true)
                .decoration(TextDecoration.ITALIC, false));

        meta.getPersistentDataContainer()
                .set(new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "isWand"),
                        PersistentDataType.BOOLEAN, true);

        wand.setItemMeta(meta);

        List<Component> lore = new ArrayList<>();

        lore.add(Component.text(""));

        lore.add(Component.text("Allows you to cast magic spells with different click combinations.")
                .decoration(TextDecoration.ITALIC, false)
                .color(TextColor.fromHexString("#ffffff")));

        // key.use key.attack key.sneak

        Component description = Component.empty();

        description = description.append(Component.text("Press [")
                .decoration(TextDecoration.ITALIC, false)
                .color(TextColor.fromHexString("#ffffff")));

        description = description.append(Component.keybind("key.drop")
                .decoration(TextDecoration.ITALIC, true)
                .color(TextColor.fromHexString("#ffffff")));

        description = description.append(Component.text("] to open your spell book.")
                .decoration(TextDecoration.ITALIC, false)
                .color(TextColor.fromHexString("#ffffff")));

        lore.add(description);

        wand.lore(lore);

        return wand;
    }
}
