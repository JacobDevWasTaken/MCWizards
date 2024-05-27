package me.jacob.mcwizards.spells.fire;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class FireCircle extends Spell {
    public FireCircle() {
        super(
                // The mana cost
                15,
                // The name of the spell
                "Fire Circle",
                // The description of the spell
                "Creates a circle of fire around you."
        );
    }

    public void spawnFallingFire(Location location) {
        World world = location.getWorld();
        FallingBlock block = world.spawn(location, FallingBlock.class);
        BlockData blockData = Material.FIRE.createBlockData();
        block.setBlockData(blockData);
    }

    @Override
    public void cast(@NotNull Player player) {
        Location start = player.getLocation().getBlock().getLocation().add(0, 5, 0);
        Vector offset = new Vector(0, 0, 5);

        for (int i = 0; i < 30; i++) {
            spawnFallingFire(new Location(start.getWorld(), start.x(), start.y(), start.z()).add(offset));
            offset.rotateAroundY(Math.PI / 15);
        }

        offset = offset.multiply(1.2);

        for (int i = 0; i < 30; i++) {
            spawnFallingFire(new Location(start.getWorld(), start.x(), start.y(), start.z()).add(offset));
            offset.rotateAroundY(Math.PI / 15);
        }
    }
}
