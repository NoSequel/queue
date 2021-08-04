package io.github.nosequel.queue.shared.model.queue.update;

import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.update.sync.DataSyncHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueueUpdateSyncHandler extends DataSyncHandler<GenericQueueData> {

    private final QueueHandler queueHandler;
    private final QueuePlayerHandler playerHandler;

    /**
     * Handle an incoming {@link GenericQueueData} object
     *
     * @param object the object to handle
     */
    @Override
    public void handleData(GenericQueueData object) {
        final QueuePlayerModel playerModel = this.playerHandler
                .find(object.getPlayerModel().getUniqueId())
                .orElse(object.getPlayerModel());

        this.queueHandler
                .find(object.getIdentifier())
                .ifPresent(model -> {
                    if (object.getType().equals(QueueUpdateType.LEAVE)) {
                        model.getEntries().remove(playerModel);
                    } else if (object.getType().equals(QueueUpdateType.JOIN)) {
                        model.getEntries().add(playerModel);
                    }
                });

        if (!this.playerHandler.hasModel(playerModel)) {
            this.playerHandler.addModel(playerModel);
        }
    }

    /**
     * Get the type of the data sync handler
     *
     * @return the type
     */
    @Override
    public Class<GenericQueueData> getType() {
        return GenericQueueData.class;
    }
}
