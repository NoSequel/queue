package io.github.nosequel.queue.shared.model.player;

import io.github.nosequel.queue.shared.model.server.ServerModel;

public interface PlayerProvider {

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

}