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

public class FireRain extends Spell {
    public FireRain() {
        super(
                // The mana cost
                15,
                // The name of the spell
                "Fire Rain",
                // The description of the spell
                "Creates a line of fire in front of you."
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
        Location start = player.getLocation().add(0, 5, 0);
        @NotNull Vector diff = player.getLocation().getDirection().setY(0).normalize().multiply(0.3);
        @NotNull Vector side = player.getLocation().getDirection().setY(0).normalize().rotateAroundY(Math.PI / 2);
        @NotNull Vector side2 = player.getLocation().getDirection().setY(0).normalize().rotateAroundY(-Math.PI / 2);

        start.add(diff.multiply(5));

        for (int i = 0; i < 25; i++) {
            spawnFallingFire(start);
            spawnFallingFire(start.add(side));
            spawnFallingFire(start.add(side2));
            start = start.add(diff);
        }
    }
}
