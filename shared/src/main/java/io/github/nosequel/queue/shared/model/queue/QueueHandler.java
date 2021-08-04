package io.github.nosequel.queue.shared.model.queue;

import io.github.nosequel.queue.shared.cache.ModelCache;
import io.github.nosequel.queue.shared.model.player.PlayerHandler;
import io.github.nosequel.queue.shared.model.player.PlayerModel;
import io.github.nosequel.queue.shared.model.queue.update.QueueMoveTask;
import io.github.nosequel.queue.shared.update.SyncHandler;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class QueueHandler extends ModelCache<String, QueueModel> {

    public QueueHandler(PlayerHandler playerHandler) {
        new QueueMoveTask(this, SyncHandler.getInstance(), playerHandler.getPlayerProvider()).start();
    }

    /**
     * Get the list of models a player is in, this method
     * loops through all cached models, and checks if the player is
     * within the {@link QueueModel#getEntries()} queue.
     *
     * @param playerModel the model of the player
     * @return the queue models found
     */
    public Collection<QueueModel> getQueue(PlayerModel playerModel) {
        final Set<QueueModel> queues = new HashSet<>();

        for (QueueModel model : this.models) {
            if (model.getEntries().contains(playerModel)) {
                queues.add(model);
            }
        }

        return queues;
    }
}