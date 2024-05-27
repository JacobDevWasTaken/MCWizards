package me.jacob.mcwizards.spells.earth;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Boulder extends Spell {
    public Boulder() {
        super(
                // The mana cost
                40,
                // The name of the spell
                "Boulder",
                // The description of the spell
                "Throws a boulder."
        );
    }

    public void spawnFallingBlock(Location location, Vector velocity) {
        World world = location.getWorld();
        FallingBlock block = world.spawn(location, FallingBlock.class);
        BlockData blockData = Material.STONE.createBlockData();
        block.setBlockData(blockData);
        block.setHurtEntities(true);
        block.setMaxDamage(10);
        block.setDamagePerBlock(10);
        block.setVelocity(velocity);
    }

    @Override
    public void cast(@NotNull Player player) {
        Location start = player.getLocation().getBlock().getLocation().add(0, 3, 0);

        spawnFallingBlock(start, player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, -1, 0), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, 1, 0), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(-1, 0, 0), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(1, 0, 0), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, 0, -1), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, 0, 1), player.getLocation().getDirection().multiply(0.8));

        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(1, 0, 1), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(1, 0, -1), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(-1, 0, 1), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(-1, 0, -1), player.getLocation().getDirection().multiply(0.8));

        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, 1, 1), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, 1, -1), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, -1, 1), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(0, -1, -1), player.getLocation().getDirection().multiply(0.8));

        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(1, 1, 0), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(1, -1, 0), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(-1, 1, 0), player.getLocation().getDirection().multiply(0.8));
        spawnFallingBlock(new Location(player.getWorld(), start.x(), start.y(), start.z()).add(-1, -1, 0), player.getLocation().getDirection().multiply(0.8));
    }
}
