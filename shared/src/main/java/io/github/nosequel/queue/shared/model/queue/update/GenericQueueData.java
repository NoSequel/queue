package io.github.nosequel.queue.shared.model.queue.update;

import io.github.nosequel.queue.shared.model.player.PlayerModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GenericQueueData {

    private final PlayerModel playerModel;
    private final QueueUpdateType type;
    private final String identifier;

}