package io.github.nosequel.queue.bukkit;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.queue.bukkit.command.QueueMetaCommand;
import io.github.nosequel.queue.bukkit.command.adapters.QueueModelTypeAdapter;
import io.github.nosequel.queue.bukkit.command.adapters.ServerModelTypeAdapter;
import io.github.nosequel.queue.bukkit.config.LangConfiguration;
import io.github.nosequel.queue.bukkit.config.QueueConfiguration;
import io.github.nosequel.queue.bukkit.config.ServerConfiguration;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BukkitQueuePlugin extends JavaPlugin {

    private final QueueBootstrap bootstrap = new QueueBootstrap(new BukkitQueuePlatform());
    private QueueConfiguration queueModelConfig;

    @Override
    public void onEnable() {
        new ServerConfiguration(new File(this.getDataFolder(), "servers.yml"));
        new LangConfiguration(new File(this.getDataFolder(), "lang.yml"));

        this.queueModelConfig = new QueueConfiguration(new File(this.getDataFolder(), "queues.yml"));

        final CommandHandler commandHandler = new BukkitCommandHandler("bukkit-queue");

        commandHandler.registerTypeAdapter(ServerModel.class, new ServerModelTypeAdapter());
        commandHandler.registerTypeAdapter(QueueModel.class, new QueueModelTypeAdapter());

        commandHandler.registerCommand(new QueueMetaCommand());
    }

    @Override
    @SneakyThrows
    public void onDisable() {
        this.queueModelConfig.save();
    }
}