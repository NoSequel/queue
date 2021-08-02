package io.github.nosequel.queue.shared.update.sync.redis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.sync.DataSyncHandler;
import io.github.nosequel.queue.shared.update.sync.redis.impl.RedisPubSub;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import java.util.function.Consumer;

public class RedisDataSyncHandler implements io.github.nosequel.queue.shared.update.sync.SyncHandler {

    private final SyncHandler syncHandler;
    private final RedisAuthorizationData authorizationData;

    private final JedisPool pool;

    public RedisDataSyncHandler(SyncHandler syncHandler, RedisAuthorizationData authorization) {
        this.syncHandler = syncHandler;
        this.authorizationData = authorization;

        this.pool = new JedisPool(authorization.getHostname(), authorization.getPort());
        this.executeCommand(jedis -> jedis.subscribe(new RedisPubSub(this), "jedis-sync"));
    }

    /**
     * Publish data to the database
     *
     * @param object the data to publish
     */
    @Override
    public void publishData(JsonObject object) {
        this.executeCommand(jedis -> jedis.publish("jedis-sync", object.toString()));
    }

    /**
     * Handle an incoming {@link JsonObject} data
     *
     * @param jsonObject the incoming data to handle
     */
    @Override
    public void handleData(JsonObject jsonObject) {

        final Gson gson = SyncHandler.GSON;

        for (DataSyncHandler<?> syncHandler : this.syncHandler.getSyncHandlers()) {
            final Class<?> clazz = syncHandler.getType();

            try {
                final Object object = gson.fromJson(jsonObject, clazz);

                syncHandler.handleDataCasted(clazz.cast(object));
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * Execute a new command using the provided {@link Consumer}
     *
     * @param consumer the command to execute
     */
    private void executeCommand(Consumer<Jedis> consumer) {
        new Thread(() -> {
            final Jedis jedis = this.pool.getResource();

            if (jedis != null) {
                if (this.authorizationData.shouldAuthorize()) {
                    jedis.auth(this.authorizationData.getPassword());
                }

                consumer.accept(jedis);
                jedis.close();
            }
        }).start();
    }
}