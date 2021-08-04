package io.github.nosequel.queue.bukkit;

import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.bukkit.BukkitConfigurationFile;
import io.github.nosequel.queue.bukkit.providers.BukkitQueuePlayerProvider;
import io.github.nosequel.queue.bukkit.providers.BukkitServerProvider;
import io.github.nosequel.queue.shared.QueuePlatform;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.sync.redis.RedisAuthorizationData;
import io.github.nosequel.queue.shared.update.sync.redis.RedisDataSyncHandler;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class BukkitQueuePlatform extends QueuePlatform {

    public BukkitQueuePlatform(File parentFile) {
        super(
                new BukkitServerProvider(),
                new BukkitQueuePlayerProvider(),
                parentFile
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