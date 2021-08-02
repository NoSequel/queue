package io.github.nosequel.queue.bukkit.config;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.annotation.Configurable;
import io.github.nosequel.config.bukkit.BukkitConfigurationFile;
import io.github.nosequel.queue.bukkit.config.adapter.QueueConfigTypeAdapter;
import io.github.nosequel.queue.bukkit.config.adapter.ServerConfigTypeAdapter;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class QueueConfiguration extends Configuration {

    private final QueueHandler queueHandler = QueueBootstrap.getBootstrap().getPlatform().getQueueHandler();

    @Configurable(path = "queues")
    public static QueueModel[] QUEUE_MODELS = new QueueModel[]{
            new QueueModel("potato"),
            new QueueModel("hors"),
    };

    @SneakyThrows
    public QueueConfiguration(File file) {
        super(new BukkitConfigurationFile(
                file,
                YamlConfiguration.loadConfiguration(file)
        ));

        this.registerAdapter(QueueModel.class, new QueueConfigTypeAdapter());
        this.load();

        // register all queue models to the QueueHandler
        for (QueueModel queueModel : QUEUE_MODELS) {
            this.queueHandler.addModel(queueModel);
        }
    }

    @SneakyThrows
    @Override
    public void save() {
        // override the old array with the new list of queue models
        QUEUE_MODELS = this.queueHandler.getModels().toArray(new QueueModel[0]);

        super.save();
    }
}