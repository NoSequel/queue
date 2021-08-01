package io.github.nosequel.queue.bukkit;

import io.github.nosequel.queue.shared.QueuePlatform;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;

public class BukkitQueuePlatform extends QueuePlatform {

    public BukkitQueuePlatform() {
        super(
                new QueueHandler(),
                new QueuePlayerHandler(),
                new ServerHandler()
        );
    }
}