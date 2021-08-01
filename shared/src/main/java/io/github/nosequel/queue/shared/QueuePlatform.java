package io.github.nosequel.queue.shared;

import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;

public interface QueuePlatform {

    QueueHandler getQueueHandler();

    QueuePlayerHandler getQueuePlayerHandler();

    ServerHandler getServerHandler();

}