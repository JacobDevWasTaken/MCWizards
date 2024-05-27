package me.jacob.mcwizards.spells.earth;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class EarthFangs extends Spell {
    public EarthFangs() {
        super(
                // The mana cost
                20,
                // The name of the spell
                "Earth Fangs",
                // The description of the spell
                "Creates a circle of evoker fangs around you."
        );
    }

    public void spawnFang(Location location) {
        World world = location.getWorld();
        world.spawn(location, EvokerFangs.class);
    }

    @Override
    public void cast(@NotNull Player player) {
        Location start = player.getLocation().getBlock().getLocation();
        Vector offset = new Vector(0, 0, 5);

        for (int i = 0; i < 20; i++) {
            spawnFang(new Location(start.getWorld(), start.x(), start.y(), start.z()).add(offset));
            offset.rotateAroundY(Math.PI / 10);
        }

        offset = offset.multiply(1.3);

        for (int i = 0; i < 26; i++) {
            spawnFang(new Location(start.getWorld(), start.x(), start.y(), start.z()).add(offset));
            offset.rotateAroundY(Math.PI / 13);
        }
    }
}
