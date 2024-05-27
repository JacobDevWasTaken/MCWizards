package me.jacob.mcwizards.spells.air;

import me.jacob.mcwizards.spellengine.Spell;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ThunderBolt extends Spell {
    public ThunderBolt() {
        super(
                35,
                "Thunder bolt",
                "Summons a thunder bolt."
        );
    }

    @Override
    public void cast(@NotNull Player player) {
        @Nullable RayTraceResult result = player.rayTraceBlocks(20);
        @NotNull Vector pos;

        pos = result == null ? player.getLocation().toVector().add(player.getLocation().getDirection().multiply(20)) : result.getHitPosition();

        player.getWorld().strikeLightning(new Location(player.getWorld(), pos.getX(), pos.getY(), pos.getZ()));
    }
}
