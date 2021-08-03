package io.github.nosequel.queue.shared;

import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.queue.QueueDataSyncHandler;
import lombok.Getter;

@Getter
public abstract class QueuePlatform {

    private final QueueHandler queueHandler;
    private final QueuePlayerHandler queuePlayerHandler;
    private final ServerHandler serverHandler;
    private final SyncHandler syncHandler;

    /**
     * Constructor to make a new {@link QueuePlatform} object.
     *
     * @param queueHandler       the handler of the queues itself
     * @param queuePlayerHandler the handler for player data
     * @param serverHandler      the handler which handles server data
     * @param syncHandler        the handler for synchronizing the data across networks
     */
    public QueuePlatform(QueueHandler queueHandler, QueuePlayerHandler queuePlayerHandler, ServerHandler serverHandler, SyncHandler syncHandler) {
        this.queueHandler = queueHandler;
        this.queuePlayerHandler = queuePlayerHandler;
        this.serverHandler = serverHandler;
        this.syncHandler = syncHandler;

        syncHandler.getSyncHandlers().add(new QueueDataSyncHandler(this.queueHandler));
    }
}