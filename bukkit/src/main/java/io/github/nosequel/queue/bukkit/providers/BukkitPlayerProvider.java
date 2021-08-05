package io.github.nosequel.queue.bukkit.providers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.bukkit.BukkitQueuePlugin;
import io.github.nosequel.queue.bukkit.util.ColorUtil;
import io.github.nosequel.queue.shared.model.player.PlayerModel;
import io.github.nosequel.queue.shared.model.player.PlayerProvider;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class BukkitPlayerProvider implements PlayerProvider {

    /**
     * Get the {@link UUID} of a {@link CommandExecutor}.
     *
     * @param executor the executor to get the unique identifier of
     * @return the unique identifier of the command executor, or null
     */
    @Override
    public UUID getUniqueId(CommandExecutor executor) {
        if (executor instanceof BukkitCommandExecutor && executor.isUser()) {
            return ((BukkitCommandExecutor) executor).getPlayer().getUniqueId();
        }

        return null;
    }

    /**
     * Send a message to a {@link PlayerModel}
     *
     * @param model   the model to send the message to
     * @param message the message to send to the model
     */
    @Override
    public void sendMessage(PlayerModel model, String message) {
        Optional.ofNullable(Bukkit.getPlayer(model.getUniqueId())).ifPresent(player -> player.sendMessage(ColorUtil.translate(message)));
    }

    /**
     * Send a {@link PlayerModel} to a target {@link ServerModel}
     *
     * @param model       the model of the player
     * @param serverModel the model to send the player to
     */
    @Override
    public void sendToServer(PlayerModel model, ServerModel serverModel) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Connect");
        out.writeUTF(serverModel.getServerName());

        final Player player = Bukkit.getPlayer(model.getUniqueId());

        player.sendPluginMessage(BukkitQueuePlugin.getPlugin(BukkitQueuePlugin.class), "BungeeCord", out.toByteArray());
    }
}