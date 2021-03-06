package io.github.nosequel.queue.shared.update.player;

import io.github.nosequel.queue.shared.model.player.PlayerHandler;
import io.github.nosequel.queue.shared.model.player.PlayerModel;
import io.github.nosequel.queue.shared.update.sync.DataSyncHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueuePlayerDataSyncHandler extends DataSyncHandler<QueuePlayerJoinData> {

    private final PlayerHandler playerHandler;

    /**
     * Handle an incoming {@link QueuePlayerJoinData} object
     *
     * @param object the object to handle
     */
    @Override
    public void handleData(QueuePlayerJoinData object) {
        if (!this.playerHandler.find(object.getUniqueId()).isPresent()) {
            if (object.getUniqueId() != null && object.getName() != null) {
                this.playerHandler.addModel(new PlayerModel(
                        object.getName(),
                        object.getUniqueId()
                ));
            }
        }
    }

    /**
     * Get the type of the data sync handler
     *
     * @return the type
     */
    @Override
    public Class<QueuePlayerJoinData> getType() {
        return QueuePlayerJoinData.class;
    }
}
