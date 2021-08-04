package io.github.nosequel.queue.bukkit;

import io.github.nosequel.queue.shared.QueuePlatform;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.sync.redis.RedisAuthorizationData;
import io.github.nosequel.queue.shared.update.sync.redis.RedisDataSyncHandler;

public class BukkitQueuePlatform extends QueuePlatform {

    public BukkitQueuePlatform(SyncHandler syncHandler, QueuePlayerHandler playerHandler, ServerHandler serverHandler) {
        super(
                new QueueHandler(playerHandler),
                playerHandler,
                serverHandler,
                syncHandler
        );

        syncHandler.setSyncHandler(new RedisDataSyncHandler(syncHandler, new RedisAuthorizationData("panel.clox.us", 6379, "")));
    }
}