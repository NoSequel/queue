package io.github.nosequel.queue.bukkit;

import io.github.nosequel.queue.shared.QueuePlatform;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;

public class BukkitQueuePlatform implements QueuePlatform {

    private final QueueHandler queueHandler = new QueueHandler();
    private final QueuePlayerHandler queuePlayerHandler = new QueuePlayerHandler();
    private final ServerHandler serverHandler = new ServerHandler();

    @Override
    public QueueHandler getQueueHandler() {
        return this.queueHandler;
    }

    @Override
    public QueuePlayerHandler getQueuePlayerHandler() {
        return this.queuePlayerHandler;
    }

    @Override
    public ServerHandler getServerHandler() {
        return this.serverHandler;
    }
}
