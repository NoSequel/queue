package io.github.nosequel.queue.bukkit;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.queue.bukkit.command.QueueMetaCommand;
import io.github.nosequel.queue.bukkit.config.LangConfiguration;
import io.github.nosequel.queue.bukkit.config.QueueConfiguration;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BukkitQueuePlugin extends JavaPlugin {

    private final QueueBootstrap bootstrap = new QueueBootstrap(new BukkitQueuePlatform());

    @Override
    public void onEnable() {
        new QueueConfiguration(new File(this.getDataFolder(), "queues.yml"));
        new LangConfiguration(new File(this.getDataFolder(), "lang.yml"));

        final CommandHandler commandHandler = new BukkitCommandHandler("bukkit-queue");
        commandHandler.registerCommand(new QueueMetaCommand());

        for (QueueModel queueModel : QueueConfiguration.QUEUE_MODELS) {
            System.out.println(queueModel.getIdentifier());
        }
    }
}