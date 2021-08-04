package io.github.nosequel.queue.bukkit.providers;

import io.github.nosequel.queue.bukkit.util.ColorUtil;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import io.github.nosequel.queue.shared.model.player.QueuePlayerProvider;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import org.bukkit.Bukkit;

import java.util.Optional;

public class BukkitQueuePlayerProvider implements QueuePlayerProvider {

    /**
     * Send a message to a {@link QueuePlayerModel}
     *
     * @param model   the model to send the message to
     * @param message the message to send to the model
     */
    @Override
    public void sendMessage(QueuePlayerModel model, String message) {
        Optional.ofNullable(Bukkit.getPlayer(model.getUniqueId())).ifPresent(player -> player.sendMessage(ColorUtil.translate(message)));
    }

    /**
     * Send a {@link QueuePlayerModel} to a target {@link ServerModel}
     *
     * @param model       the model of the player
     * @param serverModel the model to send the player to
     */
    @Override
    public void sendToServer(QueuePlayerModel model, ServerModel serverModel) {
        // todo: send to server, bungee.
    }
}
