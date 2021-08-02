package io.github.nosequel.queue.shared.update.queue;

import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.update.UpdateData;
import lombok.Getter;

import java.util.PriorityQueue;

@Getter
public class QueueUpdateData extends UpdateData<QueueModel> {

    private final ServerHandler serverHandler = QueueBootstrap.getBootstrap().getPlatform().getServerHandler();

    private final String modelId;
    private final String serverId;

    private final PriorityQueue<QueuePlayerModel> entries;

    /**
     * Constructor to make a new {@link QueueUpdateData} object.
     * <p>
     * The {@link QueueUpdateData#entries} field gets set
     * to the {@link QueueModel#getEntries()} getter method.
     * </p>
     *
     * @param queueModel the model to get the data from
     */
    public QueueUpdateData(QueueModel queueModel) {
        super();

        this.modelId = queueModel.getIdentifier();
        this.serverId = queueModel.getTargetServer().getServerName();

        this.entries = new PriorityQueue<>(queueModel.getEntries());
    }

    /**
     * Check if the update data is equal to a
     * provided {@link QueueModel} object.
     *
     * @param data the data to match against the update data
     * @return whether it's equal to the update data or not
     */
    @Override
    public boolean isEqualTo(QueueModel data) {
        return this.serverId.equals(data.getTargetServer().getServerName())
                && this.entries.containsAll(data.getEntries()) && data.getEntries().containsAll(entries);
    }

    /**
     * Update the provided {@link Object} object to
     * the current update data object.
     *
     * @param object the object to update it to
     */
    @Override
    public void updateTo(QueueModel object) {
        object.getEntries().clear();
        object.getEntries().addAll(this.entries);

        serverHandler.find(this.serverId)
                .ifPresent(object::setTargetServer);
    }
}