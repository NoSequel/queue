package io.github.nosequel.queue.bukkit.config.adapter;

import com.google.gson.*;
import io.github.nosequel.config.adapter.ConfigTypeAdapter;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.model.server.ServerModel;

import java.util.Optional;

public class QueueConfigTypeAdapter implements ConfigTypeAdapter<QueueModel> {

    private final ServerHandler serverHandler = QueueBootstrap.getBootstrap().getPlatform().getServerHandler();
    private final JsonParser parser = new JsonParser();

    @Override
    public QueueModel convert(String source) {
        final JsonObject object = this.parser.parse(source).getAsJsonObject();
        final Optional<ServerModel> serverModel = this.serverHandler.find(object.get("server_model").getAsString());

        return new QueueModel(
                object.get("identifier").getAsString(),
                serverModel.orElseGet(() -> new ServerModel("hors"))
        );
    }

    @Override
    public String convert(QueueModel queueModel) {
        final JsonObject object = new JsonObject();

        object.addProperty("identifier", queueModel.getIdentifier());

        if(queueModel.getTargetServer() != null) {
            object.addProperty("server_model", queueModel.getTargetServer().getServerName());
        } else {
            object.addProperty("server_model", "hors");
        }

        return object.toString();
    }
}