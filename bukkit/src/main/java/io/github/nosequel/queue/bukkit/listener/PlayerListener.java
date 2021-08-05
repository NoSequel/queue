package io.github.nosequel.queue.bukkit.listener;

import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.player.PlayerHandler;
import io.github.nosequel.queue.shared.model.player.PlayerProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final PlayerHandler playerHandler = QueueBootstrap.getBootstrap().getPlatform().getPlayerHandler();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final PlayerProvider provider = this.playerHandler.getPlayerProvider();

        provider.handleJoin(player.getUniqueId(), player.getName(), this.playerHandler);
    }
}