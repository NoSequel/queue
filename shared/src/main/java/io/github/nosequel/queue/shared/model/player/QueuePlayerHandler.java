package io.github.nosequel.queue.shared.model.player;

import io.github.nosequel.queue.shared.cache.ModelCache;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.player.QueuePlayerJoinData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class QueuePlayerHandler extends ModelCache<UUID, QueuePlayerModel> {

    private final SyncHandler syncHandler;
    private QueuePlayerProvider playerProvider;

    /**
     * Add a new model to the cache
     *
     * @param model the model to add to the cache
     */
    @Override
    public void addModel(QueuePlayerModel model) {
        if(!this.find(model.getUniqueId()).isPresent()) {
            this.syncHandler.pushData(new QueuePlayerJoinData(model));
        }

        super.addModel(model);
    }
}