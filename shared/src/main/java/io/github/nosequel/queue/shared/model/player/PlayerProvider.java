package io.github.nosequel.queue.shared.model.player;

import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.model.server.ServerModel;

import java.util.UUID;

public interface PlayerProvider {

    /**
     * Get the {@link UUID} of a {@link CommandExecutor}.
     *
     * @param executor the executor to get the unique identifier of
     * @return the unique identifier of the command executor, or null
     */
    UUID getUniqueId(CommandExecutor executor);

    /**
     * Send a message to a {@link PlayerModel}
     *
     * @param model   the model to send the message to
     * @param message the message to send to the model
     */
    void sendMessage(PlayerModel model, String message);

    /**
     * Send a {@link PlayerModel} to a target {@link ServerModel}
     *
     * @param model       the model of the player
     * @param serverModel the model to send the player to
     */
    void sendToServer(PlayerModel model, ServerModel serverModel);

    /**
     * Check if a {@link PlayerModel} has a provided permission
     *
     * @param playerModel the player model to check the permission for
     * @param permission  the permission to check for
     * @return whether the player model has the permission or not
     */
    boolean hasPermission(PlayerModel playerModel, String permission);

    /**
     * Handle an user joining the server.
     *
     * @param uniqueId the unique identifier of the user
     * @param name     the name of the user
     */
    default void handleJoin(UUID uniqueId, String name, PlayerHandler playerHandler) {
        if (!playerHandler.find(uniqueId).isPresent()) {
            final PlayerModel playerModel = new PlayerModel(
                    name,
                    uniqueId
            );

            playerHandler.addModel(playerModel);
        }
    }

}