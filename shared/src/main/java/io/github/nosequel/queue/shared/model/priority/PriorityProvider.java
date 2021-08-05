package io.github.nosequel.queue.shared.model.priority;

import io.github.nosequel.queue.shared.model.Model;
import io.github.nosequel.queue.shared.model.player.PlayerModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class PriorityProvider implements Model<String> {

    private final String identifier;

    /**
     * Get the queue priority of a {@link PlayerModel}.
     *
     * @param playerModel the player model to get the queue priority of
     * @return the queue priority of the player
     */
    public abstract int getPriority(PlayerModel playerModel);

    /**
     * Check if the model's identifier equals to the provided identifiers
     *
     * @param value the identifier to compare it to
     * @return whether it equals to the identifier or not
     */
    @Override
    public boolean equalsToId(String value) {
        return value.equals(this.identifier);
    }
}
