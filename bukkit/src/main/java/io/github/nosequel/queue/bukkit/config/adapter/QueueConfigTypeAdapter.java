package io.github.nosequel.queue.bukkit.config.adapter;

import com.google.gson.*;
import io.github.nosequel.config.adapter.ConfigTypeAdapter;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerHandler;

public class QueueConfigTypeAdapter implements ConfigTypeAdapter<QueueModel> {

    private final ServerHandler serverHandler = QueueBootstrap.getBootstrap().getPlatform().getServerHandler();
    private final JsonParser parser = new JsonParser();

    @Override
    public QueueModel convert(String source) {
        final JsonObject object = this.parser.parse(source).getAsJsonObject();
        final QueueModel queueModel = new QueueModel(object.get("identifier").getAsString());

        if(object.has("server_target") && !object.get("server_target").getAsString().equalsIgnoreCase("N/A")) {
            this.serverHandler.find(object.get("server_target").getAsString())
                    .ifPresent(queueModel::setTargetServer);
        }

        return queueModel;
    }

    @Override
    public String convert(QueueModel queueModel) {
        final JsonObject object = new JsonObject();

        object.addProperty("identifier", queueModel.getIdentifier());

        if(queueModel.getTargetServer() != null) {
            object.addProperty("server_target", queueModel.getTargetServer().getServerName());
        } else {
            object.addProperty("server_target", "N/A");
        }

        return object.toString();
    }
}