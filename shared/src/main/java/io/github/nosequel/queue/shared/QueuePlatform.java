package io.github.nosequel.queue.shared;

import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class QueuePlatform {

    private final QueueHandler queueHandler;
    private final QueuePlayerHandler queuePlayerHandler;
    private final ServerHandler serverHandler;

}