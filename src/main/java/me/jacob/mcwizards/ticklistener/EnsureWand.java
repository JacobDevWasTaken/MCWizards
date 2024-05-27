package me.jacob.mcwizards.ticklistener;

import me.jacob.mcwizards.wandengine.Wand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
    public class EnsureWand extends BukkitRunnable {
        @Override
        public void run() {
            for (Player player : Bukkit.getOnlinePlayers()) {
                List<ItemStack> inventory = new ArrayList<>();
                Collections.addAll(inventory, player.getInventory().getContents());
                boolean hasWand = false;
                for (ItemStack item : inventory) {
                    if (item != null && item.getType().equals(Material.BLAZE_ROD) && Wand.isWand(item))
                        if (hasWand) {
                            item.setAmount(0);
                            player.sendMessage(Component.text("You had multiple wands in your inventory, we removed some so you only have one.").color(NamedTextColor.RED));
                        } else {
                            if (item.getAmount() != 1) {
                                player.sendMessage(Component.text("You had stacked wands, we removed some so you only have one.").color(NamedTextColor.RED));
                                item.setAmount(1);
                            }
                            hasWand = true;
                        }
                    }
                    if (!hasWand) {
                    player.getInventory().addItem(Wand.getWandItem());
                    player.sendMessage(Component.text("You did not have a wand, so we gave you one.").color(NamedTextColor.RED));

            }
        }
    }

}
