package me.jacob.mcwizards.spells.earth;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class EarthFangsTwo extends Spell {
    public EarthFangsTwo() {
        super(
                // The mana cost
                20,
                // The name of the spell
                "Earth Fangs II",
                // The description of the spell
                "Creates a line of evoker fangs in front of you."
        );
    }

    public void spawnFang(Location location) {
        World world = location.getWorld();
        world.spawn(location, EvokerFangs.class);
    }

    @Override
    public void cast(@NotNull Player player) {
        Location start = player.getLocation().add(0, 0, 0);
        @NotNull Vector diff = player.getLocation().getDirection().setY(0).normalize().multiply(0.2);

        start.add(diff.multiply(10));

        for (int i = 0; i < 15; i++) {
            spawnFang(start);
            start = start.add(diff);
        }
    }
}
