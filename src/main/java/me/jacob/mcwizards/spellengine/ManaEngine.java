package me.jacob.mcwizards.spellengine;

import me.jacob.mcwizards.MCWizards;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ManaEngine {
    public static void setMana(@NotNull Player player, int mana) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "playerMana");
        PersistentDataContainer PDC = player.getPersistentDataContainer();
        PDC.set(key, PersistentDataType.INTEGER, mana);
    }

    public static int getMana(@NotNull Player player) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "playerMana");
        PersistentDataContainer PDC = player.getPersistentDataContainer();
        Integer res = PDC.get(key, PersistentDataType.INTEGER);
        if (res == null) {
            setMana(player, 0);
        }
        return Objects.requireNonNullElse(res, 0);
    }
}
