package me.jacob.mcwizards;

import me.jacob.mcwizards.commands.LBindCommand;
import me.jacob.mcwizards.commands.RBindCommand;
import me.jacob.mcwizards.listeners.*;
import me.jacob.mcwizards.ticklistener.EnsureWand;
import me.jacob.mcwizards.ticklistener.IncreaseMana;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MCWizards extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSneakListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(), this);


        new IncreaseMana().runTaskTimer(MCWizards.getPlugin(MCWizards.class), 0L, 10L);
        new EnsureWand().runTaskTimer(MCWizards.getPlugin(MCWizards.class), 0L, 100L);

        Objects.requireNonNull(this.getCommand("bindr")).setExecutor(new RBindCommand());
        Objects.requireNonNull(this.getCommand("bindl")).setExecutor(new LBindCommand());

        RegisterSpells.register();
    }
}
