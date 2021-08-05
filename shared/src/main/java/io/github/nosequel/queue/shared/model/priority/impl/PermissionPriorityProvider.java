package io.github.nosequel.queue.shared.model.priority.impl;

import io.github.nosequel.queue.shared.config.priority.PriorityConfiguration;
import io.github.nosequel.queue.shared.config.priority.permission.PermissionPriority;
import io.github.nosequel.queue.shared.model.player.PlayerModel;
import io.github.nosequel.queue.shared.model.priority.PriorityProvider;

import java.util.Arrays;

public class PermissionPriorityProvider extends PriorityProvider {

    public PermissionPriorityProvider() {
        super("permissions");
    }

    /**
     * Get the queue priority of a {@link PlayerModel}.
     *
     * @param playerModel the player model to get the queue priority of
     * @return the queue priority of the player
     */
    @Override
    public int getPriority(PlayerModel playerModel) {
        return Arrays.stream(PriorityConfiguration.PRIORITIES)
                .filter(priority -> playerModel.hasPermission(priority.getPermission()))
                .findFirst().orElse(new PermissionPriority("", 0)).getPriority();
    }
}