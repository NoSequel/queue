package io.github.nosequel.queue.shared.config.priority.adapter;

import io.github.nosequel.config.adapter.ConfigTypeAdapter;
import io.github.nosequel.queue.shared.model.priority.PriorityProviderHandler;
import io.github.nosequel.queue.shared.model.priority.impl.PermissionPriorityProvider;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.priority.PriorityProvider;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class PriorityProviderTypeAdapter implements ConfigTypeAdapter<PriorityProvider> {

    private final List<PriorityProvider> providers = Arrays.asList(
            new PermissionPriorityProvider()
    );

    @Override
    public PriorityProvider convert(String source) {
        return this.providers.stream()
                .filter(provider -> provider.getIdentifier().equalsIgnoreCase(source))
                .findFirst().orElse(null);
    }

    @Override
    public String convert(PriorityProvider priorityProvider) {
        return priorityProvider.getIdentifier();
    }
}
