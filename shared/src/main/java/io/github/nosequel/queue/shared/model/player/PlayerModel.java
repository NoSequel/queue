package io.github.nosequel.queue.shared.model.player;

import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.Model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class PlayerModel implements Model<UUID> {

    private final String name;
    private final UUID uniqueId;

    private int priority = 0;

    /**
     * Get the priority of the player model.
     * <p>
     * This method gets the priority from the
     * registered {@link io.github.nosequel.queue.shared.model.priority.PriorityProvider} from
     * the {@link io.github.nosequel.queue.shared.model.priority.PriorityProviderHandler} handler.
     * </p>
     *
     * @return the priority from the provider
     */
    public int getPriority() {
        return QueueBootstrap.getBootstrap().getPlatform().getPriorityProviderHandler().getProvider().getPriority(this);
    }

    /**
     * Check if the {@link PlayerModel} has a provided permission.
     *
     * @param permission the permission to check for
     * @return whether the player model has the permission
     */
    public boolean hasPermission(String permission) {
        return QueueBootstrap.getBootstrap().getPlatform().getPlayerHandler().getPlayerProvider().hasPermission(this, permission);
    }

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