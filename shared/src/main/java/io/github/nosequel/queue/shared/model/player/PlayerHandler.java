package io.github.nosequel.queue.shared.model.player;

import io.github.nosequel.queue.shared.cache.ModelCache;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.player.QueuePlayerJoinData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class PlayerHandler extends ModelCache<UUID, PlayerModel> {

    private final SyncHandler syncHandler;
    private final PlayerProvider playerProvider;

    /**
     * Add a new model to the cache
     *
     * @param model the model to add to the cache
     */
    @Override
    public void addModel(PlayerModel model) {
        if(!this.find(model.getUniqueId()).isPresent()) {
            this.syncHandler.pushData(new QueuePlayerJoinData(model));
        }

        super.addModel(model);
    }
}