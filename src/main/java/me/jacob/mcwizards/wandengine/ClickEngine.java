package me.jacob.mcwizards.wandengine;

import me.jacob.mcwizards.MCWizards;
import me.jacob.mcwizards.ui.SpellCombinationUI;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClickEngine {
    public static void setCombination(@NotNull Player player, List<Boolean> combination) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "playerClickCombination");
        PersistentDataContainer PDC = player.getPersistentDataContainer();

        PDC.set(key, PersistentDataType.LIST.listTypeFrom(PersistentDataType.BOOLEAN), combination);
    }

    public static @NotNull List<Boolean> getCombination(@NotNull Player player) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "playerClickCombination");
        PersistentDataContainer PDC = player.getPersistentDataContainer();

        return Objects.requireNonNullElse(PDC.get(key, PersistentDataType.LIST.listTypeFrom(PersistentDataType.BOOLEAN)), new ArrayList<>());
    }

    public static void setLastClickTimestamp(@NotNull Player player, long time) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "lastClickTimestamp");
        PersistentDataContainer PDC = player.getPersistentDataContainer();
        PDC.set(key, PersistentDataType.LONG, time);
    }

    public static long getLastClickTimestamp(@NotNull Player player) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "lastClickTimestamp");
        PersistentDataContainer PDC = player.getPersistentDataContainer();
        Long res = PDC.get(key, PersistentDataType.LONG);
        if (res == null) {
            setLastClickTimestamp(player, 0L);
        }
        return Objects.requireNonNullElse(res, 0L);
    }

    public static void addToCombination(@NotNull Player player, @NotNull Boolean b) {
        List<Boolean> combination = new ArrayList<>(getCombination(player));
        combination.add(b);
        setCombination(player, combination);
    }

    public static void removeCombination(@NotNull Player player) {
        List<Boolean> combination = new ArrayList<>();
        setCombination(player, combination);
    }

    public static void handleClick(@NotNull Player player, boolean clickType) {
        if (getLastClickTimestamp(player) + 1100 < System.currentTimeMillis()) {
            removeCombination(player);
        }

        if (getCombination(player).size() > 3) {return;}

        if (getLastClickTimestamp(player) + 50 > System.currentTimeMillis()) {return;}

        addToCombination(player, clickType);

        SpellCombinationUI.showCombination(player, getCombination(player));

        Sound sound = Sound.sound(Key.key("minecraft:entity.experience_orb.pickup"), Sound.Source.MUSIC, 1f, 1f);
        player.playSound(sound);

        setLastClickTimestamp(player, System.currentTimeMillis());
    }
}
