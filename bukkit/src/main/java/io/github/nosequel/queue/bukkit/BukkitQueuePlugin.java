package io.github.nosequel.queue.bukkit;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.queue.bukkit.command.QueueJoinCommand;
import io.github.nosequel.queue.bukkit.command.QueueMetaCommand;
import io.github.nosequel.queue.bukkit.command.adapters.QueueModelTypeAdapter;
import io.github.nosequel.queue.bukkit.command.adapters.ServerModelTypeAdapter;
import io.github.nosequel.queue.bukkit.listener.PlayerListener;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitQueuePlugin extends JavaPlugin {

    private QueueBootstrap bootstrap;

    @Override
    public void onEnable() {
        // register bungeecord
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.bootstrap = new QueueBootstrap(new BukkitQueuePlatform(this.getDataFolder()));

        final CommandHandler commandHandler = new BukkitCommandHandler("bukkit-queue");

        // register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        // register commands
        commandHandler.registerTypeAdapter(ServerModel.class, new ServerModelTypeAdapter());
        commandHandler.registerTypeAdapter(QueueModel.class, new QueueModelTypeAdapter());

        commandHandler.registerCommand(new QueueMetaCommand());
        commandHandler.registerCommand(new QueueJoinCommand());
    }

    @Override
    @SneakyThrows
    public void onDisable() {
        this.bootstrap.getPlatform().unload();
    }
}