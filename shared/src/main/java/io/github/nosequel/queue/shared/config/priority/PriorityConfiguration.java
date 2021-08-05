package io.github.nosequel.queue.shared.config.priority;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;
import io.github.nosequel.queue.shared.config.priority.adapter.PriorityProviderTypeAdapter;
import io.github.nosequel.queue.shared.config.priority.permission.PermissionPriority;
import io.github.nosequel.queue.shared.model.priority.PriorityProvider;
import io.github.nosequel.queue.shared.model.priority.impl.PermissionPriorityProvider;
import lombok.SneakyThrows;

public class PriorityConfiguration extends Configuration {

    @Configurable(path = "provider.provider")
    public static PriorityProvider PROVIDER = new PermissionPriorityProvider();

    @Configurable(path = "provider.permission")
    public static PermissionPriority[] PRIORITIES = new PermissionPriority[]{
            new PermissionPriority("example.permission", 0),
            new PermissionPriority("higher.example.permission", 1)
    };

    @SneakyThrows
    public PriorityConfiguration(ConfigurationFile file) {
        super(file);

        this.registerAdapter(PriorityProvider.class, new PriorityProviderTypeAdapter());

        this.load();
        this.save();
    }
}