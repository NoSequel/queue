package io.github.nosequel.queue.shared.model.queue.update;

import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GenericQueueData {

    private final QueuePlayerModel playerModel;
    private final QueueUpdateType type;
    private final String identifier;

}