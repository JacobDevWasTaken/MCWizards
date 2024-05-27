package me.jacob.mcwizards.commands;

import me.jacob.mcwizards.MCWizards;
import me.jacob.mcwizards.RegisterSpells;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LBindCommand implements CommandExecutor {
    public static void setBindCombination(@NotNull Player player, List<Boolean> combination) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "BoundToL");
        PersistentDataContainer PDC = player.getPersistentDataContainer();

        PDC.set(key, PersistentDataType.LIST.listTypeFrom(PersistentDataType.BOOLEAN), combination);
    }

    public static @NotNull List<Boolean> getBindCombination(@NotNull Player player) {
        NamespacedKey key = new NamespacedKey(MCWizards.getPlugin(MCWizards.class), "BoundToL");
        PersistentDataContainer PDC = player.getPersistentDataContainer();

        return Objects.requireNonNullElse(PDC.get(key, PersistentDataType.LIST.listTypeFrom(PersistentDataType.BOOLEAN)), new ArrayList<>());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                ArrayList<Boolean> combination = new ArrayList<>();
                char x = 'r';
                for (Character character : args[0].toLowerCase().toCharArray()) {
                    combination.add(character == x);
                }
                setBindCombination((Player) sender, combination);

                ((Player) sender).sendMessage("Bound combination L to " + RegisterSpells.getSpell(combination).name);
            }
        }
        return true;
    }
}