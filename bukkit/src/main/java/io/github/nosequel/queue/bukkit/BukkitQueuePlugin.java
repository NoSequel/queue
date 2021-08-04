package io.github.nosequel.queue.bukkit;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.command.bukkit.BukkitCommandHandler;
import io.github.nosequel.queue.bukkit.command.QueueJoinCommand;
import io.github.nosequel.queue.bukkit.command.QueueMetaCommand;
import io.github.nosequel.queue.bukkit.command.adapters.QueueModelTypeAdapter;
import io.github.nosequel.queue.bukkit.command.adapters.ServerModelTypeAdapter;
import io.github.nosequel.queue.bukkit.config.LangConfiguration;
import io.github.nosequel.queue.bukkit.config.QueueConfiguration;
import io.github.nosequel.queue.bukkit.config.ServerConfiguration;
import io.github.nosequel.queue.bukkit.listener.PlayerListener;
import io.github.nosequel.queue.bukkit.providers.BukkitQueuePlayerProvider;
import io.github.nosequel.queue.bukkit.providers.BukkitServerProvider;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import io.github.nosequel.queue.shared.update.SyncHandler;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BukkitQueuePlugin extends JavaPlugin {

    private QueueBootstrap bootstrap;
    private QueueConfiguration queueModelConfig;

    @Override
    public void onEnable() {
        // register bungeecord
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        new ServerConfiguration(new File(this.getDataFolder(), "servers.yml"));
        new LangConfiguration(new File(this.getDataFolder(), "lang.yml"));

        final SyncHandler syncHandler = new SyncHandler();
        final ServerHandler serverHandler = new ServerHandler(ServerConfiguration.LOCAL_SERVER, syncHandler);

        final QueuePlayerHandler playerHandler = new QueuePlayerHandler(syncHandler);

        serverHandler.setProvider(new BukkitServerProvider());
        playerHandler.setPlayerProvider(new BukkitQueuePlayerProvider());

        this.bootstrap = new QueueBootstrap(new BukkitQueuePlatform(
                syncHandler,
                playerHandler,
                serverHandler
        ));
        this.loadServerData();

        this.queueModelConfig = new QueueConfiguration(new File(this.getDataFolder(), "queues.yml"));
        this.loadQueueData();

        final CommandHandler commandHandler = new BukkitCommandHandler("bukkit-queue");

        // register listeners
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        // register commands
        commandHandler.registerTypeAdapter(ServerModel.class, new ServerModelTypeAdapter());
        commandHandler.registerTypeAdapter(QueueModel.class, new QueueModelTypeAdapter());

        commandHandler.registerCommand(new QueueMetaCommand());
        commandHandler.registerCommand(new QueueJoinCommand());
    }

    private void loadServerData() {
        this.bootstrap.getPlatform().getServerHandler().addModel(ServerConfiguration.LOCAL_SERVER);

        for (ServerModel serverModel : ServerConfiguration.SERVER_MODELS) {
            this.bootstrap.getPlatform().getServerHandler().addModel(serverModel);
        }
    }

    private void loadQueueData() {
        for (QueueModel queueModel : QueueConfiguration.QUEUE_MODELS) {
            this.bootstrap.getPlatform().getQueueHandler().addModel(queueModel);
        }
    }

    @Override
    @SneakyThrows
    public void onDisable() {
        this.queueModelConfig.save();
    }
}