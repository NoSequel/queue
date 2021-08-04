package io.github.nosequel.queue.bukkit;

import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.queue.bukkit.command.QueueJoinCommand;
import io.github.nosequel.queue.bukkit.listener.PlayerListener;
import io.github.nosequel.queue.shared.QueueBootstrap;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitQueuePlugin extends JavaPlugin {

    private QueueBootstrap bootstrap;

    @Override
    public void onEnable() {
        final BukkitCommandHandler commandHandler = new BukkitCommandHandler("bukkit-queue");

        // register bungeecord
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // setup the bootstrap class
        this.bootstrap = new QueueBootstrap(new BukkitQueuePlatform(this.getDataFolder(), commandHandler));

        // register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        // register commands
        commandHandler.registerCommand(new QueueJoinCommand());
    }

    @Override
    @SneakyThrows
    public void onDisable() {
        this.bootstrap.getPlatform().unload();
    }
}