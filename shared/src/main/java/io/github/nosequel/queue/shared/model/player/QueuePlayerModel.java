package io.github.nosequel.queue.shared.model.player;

import io.github.nosequel.queue.shared.model.Model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class QueuePlayerModel implements Model<UUID> {

    private final String name;
    private final UUID uniqueId;

    private int priority;

    /**
     * Check if the model's identifier equals to the provided identifiers
     *
     * @param value the identifier to compare it to
     * @return whether it equals to the identifier or not
     */
    @Override
    public boolean equalsToId(UUID value) {
        return this.uniqueId.equals(value);
    }
}