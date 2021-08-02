package io.github.nosequel.queue.shared.update.sync.redis.impl;

import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.sync.redis.RedisDataSyncHandler;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.JedisPubSub;

@RequiredArgsConstructor
public class RedisPubSub extends JedisPubSub {

    private final RedisDataSyncHandler syncHandler;

    @Override
    public void onMessage(String channel, String message) {
        this.syncHandler.handleData(SyncHandler.PARSER.parse(message).getAsJsonObject());
    }
}
