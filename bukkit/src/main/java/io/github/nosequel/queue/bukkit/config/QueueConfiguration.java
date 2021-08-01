package io.github.nosequel.queue.bukkit.config;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.annotation.Configurable;
import io.github.nosequel.config.bukkit.BukkitConfigurationFile;
import io.github.nosequel.queue.bukkit.config.adapter.QueueConfigTypeAdapter;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class QueueConfiguration extends Configuration {

    @Configurable(path = "queues")
    public static QueueModel[] QUEUE_MODELS = new QueueModel[]{
            new QueueModel("potato", null),
            new QueueModel("hors", null),
    };

    @SneakyThrows
    public QueueConfiguration(File file) {
        super(new BukkitConfigurationFile(
                file,
                YamlConfiguration.loadConfiguration(file)
        ));

        this.registerAdapter(QueueModel.class, new QueueConfigTypeAdapter());

        System.out.println(QUEUE_MODELS.getClass().getSimpleName());

        this.load();
        this.save();
    }
}