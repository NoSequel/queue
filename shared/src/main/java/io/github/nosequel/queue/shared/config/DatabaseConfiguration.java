package io.github.nosequel.queue.shared.config;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;
import io.github.nosequel.queue.shared.update.sync.redis.RedisAuthorizationData;
import lombok.SneakyThrows;

public class DatabaseConfiguration extends Configuration {

    @Configurable(path = "redis.authorization")
    public static RedisAuthorizationData AUTHORIZATION_DATA = new RedisAuthorizationData(
            "127.0.0.1",
            6379,
            ""
    );

    @SneakyThrows
    public DatabaseConfiguration(ConfigurationFile file) {
        super(file);

        this.load();
        this.save();
    }
}
