package io.github.nosequel.queue.shared.model.queue;

import io.github.nosequel.queue.shared.model.Model;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.PriorityQueue;

@Getter
@Setter
@RequiredArgsConstructor
public class QueueModel implements Model<String> {

    private final PriorityQueue<QueuePlayerModel> entries = new PriorityQueue<>();

    private final String identifier;
    private ServerModel targetServer;

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