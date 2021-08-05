package io.github.nosequel.queue.shared.model.queue;

import io.github.nosequel.queue.shared.model.Model;
import io.github.nosequel.queue.shared.model.player.PlayerModel;
import io.github.nosequel.queue.shared.model.queue.exception.AlreadyContainsModelException;
import io.github.nosequel.queue.shared.model.queue.update.GenericQueueData;
import io.github.nosequel.queue.shared.model.queue.update.QueueUpdateType;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import io.github.nosequel.queue.shared.update.SyncHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
public class QueueModel implements Model<String> {

    private final PriorityQueue<PlayerModel> entries = new PriorityQueue<>(Comparator.comparingInt(playerModel -> -playerModel.getPriority()));
    private final Set<QueueModelMetadata> metadatum = new HashSet<>();

    private final String identifier;
    private ServerModel targetServer;

    /**
     * Get the current queue position of a {@link PlayerModel}
     * within a {@link QueueModel}.
     *
     * @param playerModel the player to find within the queue
     * @return the position of the player in the queue, if for some reason
     * unable to find within the queue it will return -1.
     */
    public Integer getPosition(PlayerModel playerModel) {
        if (!this.entries.contains(playerModel)) {
            throw new IllegalArgumentException("playerModel with unique identifier " + playerModel.getUniqueId().toString() + " is not in the " + this.identifier + " queue.");
        }

        for (int i = 0; i < this.entries.size(); i++) {
            final PlayerModel current = this.entries.peek();

            if (current != null && current.equals(playerModel)) {
                return i + 1;
            }
        }

        return -1;
    }

    /**
     * Add an entry to the {@link QueueModel#entries} queue
     *
     * @param playerModel the model to add to the queue
     */
    public void addEntry(PlayerModel playerModel) throws AlreadyContainsModelException {
        if(this.getEntries().contains(playerModel)) {
            throw new AlreadyContainsModelException("Tried to add model with UUID " + playerModel.getUniqueId() + " to queue, but queue already contains this model.");
        }

        this.entries.add(playerModel);

        SyncHandler.getInstance().pushData(new GenericQueueData(
                playerModel,
                QueueUpdateType.JOIN,
                this.identifier
        ));
    }

    /**
     * Add an array of {@link QueueModelMetadata} to the {@link QueueModel#metadatum} field
     *
     * @param metadatum the metadata to add
     */
    public void addMetadata(QueueModelMetadata... metadatum) {
        this.metadatum.addAll(Arrays.asList(metadatum));
    }

    /**
     * Remove an array of {@link QueueModelMetadata} from the {@link QueueModel#metadatum} field.
     *
     * @param metadatum the metadata to remove
     */
    public void removeMetadata(QueueModelMetadata... metadatum) {
        Arrays.asList(metadatum).forEach(this.metadatum::remove);
    }

    /**
     * Check if the {@link QueueModel#metadatum} field contains the
     * provided {@link QueueModelMetadata} metadata value.
     *
     * @param metadata the metadata value to check for
     * @return whether it has the metadata or not
     */
    public boolean hasMetadata(QueueModelMetadata metadata) {
        return this.metadatum.contains(metadata);
    }

    /**
     * Check if the model's identifier equals to the provided identifiers
     *
     * @param value the identifier to compare it to
     * @return whether it equals to the identifier or not
     */
    @Override
    public boolean equalsToId(String value) {
        return this.identifier.equalsIgnoreCase(value);
    }
}