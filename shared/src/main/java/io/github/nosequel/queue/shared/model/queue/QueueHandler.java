package io.github.nosequel.queue.shared.model.queue;

import io.github.nosequel.queue.shared.cache.ModelCache;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class QueueHandler extends ModelCache<String, QueueModel> {

    /**
     * Get the current queue position of a {@link QueuePlayerModel}
     * within a {@link QueueModel}.
     *
     * @param playerModel the player to find within the queue
     * @param queueModel  the queue to find the player in
     * @return the position of the player in the queue, if for some reason
     *         unable to find within the queue it will return -1.
     */
    public Integer getPosition(QueuePlayerModel playerModel, QueueModel queueModel) {
        if(!queueModel.getEntries().contains(playerModel)) {
            throw new IllegalArgumentException("playerModel with unique identifier " + playerModel.getUniqueId().toString() + " is not in the " + queueModel.getIdentifier() + " queue.");
        }

        for(int i = 0; i < queueModel.getEntries().size(); i++) {
            final QueuePlayerModel current = queueModel.getEntries().poll();

            if(current != null && current.equals(playerModel)) {
                return i + 1;
            }
        }

        return -1;
    }

    /**
     * Get the list of models a player is in, this method
     * loops through all cached models, and checks if the player is
     * within the {@link QueueModel#getEntries()} queue.
     *
     * @param playerModel the model of the player
     * @return the queue models found
     */
    public Collection<QueueModel> getQueue(QueuePlayerModel playerModel) {
        return this.models.stream()
                .filter(model -> model.getEntries().contains(playerModel))
                .collect(Collectors.toCollection(HashSet::new));
    }
}