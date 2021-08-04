package io.github.nosequel.queue.bukkit.listener;

import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final QueuePlayerHandler playerHandler = QueueBootstrap.getBootstrap().getPlatform().getQueuePlayerHandler();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!this.playerHandler.find(player.getUniqueId()).isPresent()) {
            final QueuePlayerModel playerModel = new QueuePlayerModel(
                    player.getName(),
                    player.getUniqueId()
            );

            this.playerHandler.addModel(playerModel);
        }
    }
}