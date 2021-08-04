package io.github.nosequel.queue.bukkit;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.bukkit.BukkitConfigurationFile;
import io.github.nosequel.queue.bukkit.providers.BukkitPlayerProvider;
import io.github.nosequel.queue.bukkit.providers.BukkitServerProvider;
import io.github.nosequel.queue.shared.QueuePlatform;
import io.github.nosequel.queue.shared.model.player.PlayerProvider;
import io.github.nosequel.queue.shared.model.server.ServerProvider;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class BukkitQueuePlatform extends QueuePlatform {

    /**
     * Constructor to make a new {@link BukkitQueuePlatform} object.
     *
     * @param parentFile     the parent file to provide in the {@link QueuePlatform#QueuePlatform(ServerProvider, PlayerProvider, File, CommandHandler)} constructor.
     * @param commandHandler the command handler to provide in the {@link QueuePlatform#QueuePlatform(ServerProvider, PlayerProvider, File, CommandHandler)} constructor.
     */
    public BukkitQueuePlatform(File parentFile, BukkitCommandHandler commandHandler) {
        super(
                new BukkitServerProvider(),
                new BukkitPlayerProvider(),
                parentFile,
                commandHandler
        );
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