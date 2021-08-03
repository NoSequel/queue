package io.github.nosequel.queue.shared.update;

import com.google.gson.*;
import io.github.nosequel.queue.shared.update.sync.DataSyncHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SyncHandler {

    @Getter
    private static SyncHandler instance; // the main instance for the SyncHandler object

    public final static Gson GSON = new GsonBuilder()
            .setLongSerializationPolicy(LongSerializationPolicy.STRING)
            .setPrettyPrinting().create();

    public final static JsonParser PARSER = new JsonParser();

    private final List<DataSyncHandler<?>> syncHandlers = new ArrayList<>();
    private io.github.nosequel.queue.shared.update.sync.SyncHandler syncHandler;

    public SyncHandler() {
        instance = this;
    }

    /**
     * Push an {@link Object} as data to the {@link io.github.nosequel.queue.shared.update.sync.SyncHandler}.
     * This method automatically parses the {@link Object} into a {@link JsonObject}
     * using {@link Gson} and {@link JsonParser}.
     *
     * @param object the object to parse and publish
     */
    public void pushData(Object object) {
        final String jsonString = GSON.toJson(object);
        final JsonObject jsonObject = PARSER.parse(jsonString).getAsJsonObject();

        this.syncHandler.publishData(jsonObject);
    }
}