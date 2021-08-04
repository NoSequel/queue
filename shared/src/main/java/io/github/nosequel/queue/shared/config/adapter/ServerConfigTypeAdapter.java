package io.github.nosequel.queue.shared.config.adapter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.nosequel.config.adapter.ConfigTypeAdapter;
import io.github.nosequel.queue.shared.model.server.ServerModel;

public class ServerConfigTypeAdapter implements ConfigTypeAdapter<ServerModel> {

    private final JsonParser parser = new JsonParser();

    @Override
    public ServerModel convert(String source) {
        return new ServerModel(this.parser.parse(source).getAsJsonObject().get("identifier").getAsString());
    }

    @Override
    public String convert(ServerModel serverModel) {
        final JsonObject object = new JsonObject();
        object.addProperty("identifier", serverModel.getServerName());

        return object.toString();
    }
}
