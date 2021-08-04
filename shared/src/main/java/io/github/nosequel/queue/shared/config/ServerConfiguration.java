package io.github.nosequel.queue.shared.config;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;

import io.github.nosequel.queue.shared.config.adapter.ServerConfigTypeAdapter;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import lombok.SneakyThrows;

public class ServerConfiguration  extends Configuration {

    @Configurable(path = "local-server")
    public static ServerModel LOCAL_SERVER = new ServerModel("dev-1");

    @Configurable(path = "servers")
    public static ServerModel[] SERVER_MODELS = new ServerModel[]{
            new ServerModel("hcteams-eu")
    };

    @SneakyThrows
    public ServerConfiguration(ConfigurationFile file) {
        super(file);

        this.registerAdapter(ServerModel.class, new ServerConfigTypeAdapter());

        this.load();
        this.save();
    }
}
