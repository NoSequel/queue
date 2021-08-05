package io.github.nosequel.queue.shared.model.priority;

import io.github.nosequel.queue.shared.cache.ModelCache;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PriorityProviderHandler extends ModelCache<String, PriorityProvider> {

    private PriorityProvider provider;

}
