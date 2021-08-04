package io.github.nosequel.queue.shared.update.player;

import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import lombok.Getter;

import java.util.UUID;

@Getter
public class QueuePlayerJoinData {

    private final UUID uniqueId;
    private final String name;

    public QueuePlayerJoinData(QueuePlayerModel model) {
        this.uniqueId = model.getUniqueId();
        this.name = model.getName();
    }

}
