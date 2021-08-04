package io.github.nosequel.queue.bukkit;

import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.bukkit.BukkitConfigurationFile;
import io.github.nosequel.queue.bukkit.providers.BukkitPlayerProvider;
import io.github.nosequel.queue.bukkit.providers.BukkitServerProvider;
import io.github.nosequel.queue.shared.QueuePlatform;
import io.github.nosequel.queue.shared.update.sync.redis.RedisAuthorizationData;
import io.github.nosequel.queue.shared.update.sync.redis.RedisDataSyncHandler;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class BukkitQueuePlatform extends QueuePlatform {

    public BukkitQueuePlatform(File parentFile, BukkitCommandHandler commandHandler) {
        super(
                new BukkitServerProvider(),
                new BukkitPlayerProvider(),
                parentFile,
                commandHandler
        );

        this.getSyncHandler().setSyncHandler(new RedisDataSyncHandler(this.getSyncHandler(), new RedisAuthorizationData(
                "panel.clox.us",
                6379,
                "")
        ));
    }

    /**
     * Create a new {@link ConfigurationFile} object with the
     * provided {@link File} object.
     *
     * @param file the file to use to make the new configuration file
     * @return the configuration file
     */
    @Override
    public ConfigurationFile createConfigurationFile(File file) {
        return new BukkitConfigurationFile(
                file,
                YamlConfiguration.loadConfiguration(file)
        );
    }
}