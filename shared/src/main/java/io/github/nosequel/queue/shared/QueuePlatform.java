package io.github.nosequel.queue.shared;

import io.github.nosequel.command.CommandHandler;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.queue.shared.command.QueueJoinCommand;
import io.github.nosequel.queue.shared.command.QueueMetaCommand;
import io.github.nosequel.queue.shared.command.adapters.QueueModelTypeAdapter;
import io.github.nosequel.queue.shared.command.adapters.ServerModelTypeAdapter;
import io.github.nosequel.queue.shared.config.DatabaseConfiguration;
import io.github.nosequel.queue.shared.config.LangConfiguration;
import io.github.nosequel.queue.shared.config.QueueConfiguration;
import io.github.nosequel.queue.shared.config.ServerConfiguration;
import io.github.nosequel.queue.shared.model.player.PlayerHandler;
import io.github.nosequel.queue.shared.model.player.PlayerProvider;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.queue.update.QueueUpdateSyncHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import io.github.nosequel.queue.shared.model.server.ServerProvider;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.player.QueuePlayerDataSyncHandler;
import io.github.nosequel.queue.shared.update.server.ServerDataSyncHandler;
import io.github.nosequel.queue.shared.update.sync.redis.RedisDataSyncHandler;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public abstract class QueuePlatform {

    private final QueueHandler queueHandler;
    private final PlayerHandler playerHandler;
    private final ServerHandler serverHandler;
    private final SyncHandler syncHandler;

    private final QueueConfiguration queueModelConfig;
    private final ServerConfiguration serverConfiguration;
    private final LangConfiguration langConfiguration;
    private final DatabaseConfiguration databaseConfiguration;

    /**
     * Constructor to make a new {@link QueuePlatform} object.
     *
     * @param serverProvider the provider provided in the {@link ServerHandler#ServerHandler(ServerModel, SyncHandler, ServerProvider)} constructor
     * @param playerProvider the provider provided in the {@link PlayerHandler#PlayerHandler(SyncHandler, PlayerProvider)} constructor
     * @param parentFolder   the parent folder to create the files in
     */
    public QueuePlatform(ServerProvider serverProvider, PlayerProvider playerProvider, File parentFolder, CommandHandler commandHandler) {
        this.syncHandler = new SyncHandler();

        this.playerHandler = new PlayerHandler(this.syncHandler, playerProvider);
        this.queueHandler = new QueueHandler(this.playerHandler);

        // register server configuration before server handler,
        // the server handler requires a field from the server configuration
        this.serverConfiguration = new ServerConfiguration(this.createConfigurationFile(new File(parentFolder, "servers.yml")));

        // register database configuration for server handler synchronization
        this.databaseConfiguration = new DatabaseConfiguration(this.createConfigurationFile(new File(parentFolder, "database.yml")));

        // register the previously mentioned server handler,
        // using the ServerConfiguration.LOCAL_SERVER field.
        this.serverHandler = new ServerHandler(ServerConfiguration.LOCAL_SERVER, this.syncHandler, serverProvider);
        this.syncHandler.setSyncHandler(new RedisDataSyncHandler(
                this.syncHandler,
                DatabaseConfiguration.AUTHORIZATION_DATA
        ));

        // register all synchronization handlers
        syncHandler.getSyncHandlers().add(new QueueUpdateSyncHandler(this.queueHandler, this.playerHandler));
        syncHandler.getSyncHandlers().add(new QueuePlayerDataSyncHandler(this.playerHandler));
        syncHandler.getSyncHandlers().add(new ServerDataSyncHandler(this.serverHandler));

        // register configuration stuff
        this.langConfiguration = new LangConfiguration(this.createConfigurationFile(new File(parentFolder, "lang.yml")));

        // load server data before loading the queue configuration
        this.loadServerData();

        this.queueModelConfig = new QueueConfiguration(this.createConfigurationFile(new File(parentFolder, "queues.yml")), this);

        // load the queue data after the queue configuration & server data
        this.loadQueueData();

        // register commands
        commandHandler.registerTypeAdapter(ServerModel.class, new ServerModelTypeAdapter(this.serverHandler));
        commandHandler.registerTypeAdapter(QueueModel.class, new QueueModelTypeAdapter(this.queueHandler));

        commandHandler.registerCommand(new QueueMetaCommand(this.queueHandler));
        commandHandler.registerCommand(new QueueJoinCommand(this.playerHandler));
    }

    /**
     * Create a new {@link ConfigurationFile} object with the
     * provided {@link File} object.
     *
     * @param file the file to use to make the new configuration file
     * @return the configuration file
     */
    public abstract ConfigurationFile createConfigurationFile(File file);

    public void loadServerData() {
        for (ServerModel serverModel : ServerConfiguration.SERVER_MODELS) {
            this.serverHandler.addModel(serverModel);
        }
    }

    public void loadQueueData() {
        for (QueueModel queueModel : QueueConfiguration.QUEUE_MODELS) {
            this.queueHandler.addModel(queueModel);
        }
    }


    public void unload() {
        if (this.queueModelConfig != null) {
            this.queueModelConfig.save();
        }
    }
}