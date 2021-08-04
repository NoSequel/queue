package io.github.nosequel.queue.shared.model.player;

import io.github.nosequel.queue.shared.model.server.ServerModel;

public interface QueuePlayerProvider {

    /**
     * Send a message to a {@link QueuePlayerModel}
     *
     * @param model   the model to send the message to
     * @param message the message to send to the model
     */
    void sendMessage(QueuePlayerModel model, String message);

    /**
     * Send a {@link QueuePlayerModel} to a target {@link ServerModel}
     *
     * @param model       the model of the player
     * @param serverModel the model to send the player to
     */
    void sendToServer(QueuePlayerModel model, ServerModel serverModel);

}