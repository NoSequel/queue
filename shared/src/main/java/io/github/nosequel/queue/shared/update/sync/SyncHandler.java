package io.github.nosequel.queue.shared.update.sync;

import com.google.gson.JsonObject;

public interface SyncHandler {

    /**
     * Publish data to the database
     *
     * @param object the data to publish
     */
    void publishData(JsonObject object);

    /**
     * Handle an incoming {@link JsonObject} data
     *
     * @param object the incoming data to handle
     */
    void handleData(JsonObject object);

}
