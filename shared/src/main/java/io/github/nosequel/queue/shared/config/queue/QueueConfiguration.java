package io.github.nosequel.queue.shared.config.queue;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;
import io.github.nosequel.queue.shared.QueuePlatform;
import io.github.nosequel.queue.shared.config.queue.adapter.QueueConfigTypeAdapter;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import lombok.SneakyThrows;

public class QueueConfiguration extends Configuration {

    private final QueueHandler queueHandler;

    @Configurable(path = "queues")
    public static QueueModel[] QUEUE_MODELS = new QueueModel[]{
            new QueueModel("potato"),
            new QueueModel("hors"),
    };

    @SneakyThrows
    public QueueConfiguration(ConfigurationFile file, QueuePlatform platform) {
        super(file);

        this.queueHandler = platform.getQueueHandler();
        this.registerAdapter(QueueModel.class, new QueueConfigTypeAdapter(platform.getServerHandler()));
        this.load();
    }

    @SneakyThrows
    @Override
    public void save() {
        // override the old array with the new list of queue models
        QUEUE_MODELS = this.queueHandler.getModels().toArray(new QueueModel[0]);

        super.save();
    }
}