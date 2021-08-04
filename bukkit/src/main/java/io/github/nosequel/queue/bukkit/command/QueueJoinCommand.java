package io.github.nosequel.queue.bukkit.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.nosequel.queue.shared.config.LangConfiguration;
import io.github.nosequel.queue.bukkit.util.ColorUtil;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import io.github.nosequel.queue.shared.model.queue.QueueModel;

public class QueueJoinCommand {

    private final QueuePlayerHandler playerHandler = QueueBootstrap.getBootstrap().getPlatform().getQueuePlayerHandler();

    @Command(label = "queue", aliases = {"joinqueue", "join"}, permission = "queue.bukkit.join", userOnly = true)
    public void queue(BukkitCommandExecutor executor, QueueModel target) {
        final QueuePlayerModel model = this.playerHandler
                .find(executor.getPlayer().getUniqueId())
                .orElse(null);

        executor.sendMessage(ColorUtil.translate(LangConfiguration.QUEUE_JOIN.replace("%queue_name%", target.getIdentifier())));
        target.addEntry(model);
    }
}