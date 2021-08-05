package io.github.nosequel.queue.shared.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.config.LangConfiguration;
import io.github.nosequel.queue.shared.model.player.PlayerHandler;
import io.github.nosequel.queue.shared.model.player.PlayerModel;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.queue.exception.AlreadyContainsModelException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueueJoinCommand {

    private final PlayerHandler playerHandler;

    @Command(label = "queue", aliases = {"joinqueue", "join"}, permission = "queue.bukkit.join", userOnly = true)
    public void queue(CommandExecutor executor, QueueModel target) {
        final PlayerModel model = this.playerHandler
                .find(this.playerHandler.getPlayerProvider().getUniqueId(executor))
                .orElse(null);

        try {
            target.addEntry(model);
            executor.sendMessage(LangConfiguration.QUEUE_JOIN.replace("%queue_name%", target.getIdentifier()));
        } catch (AlreadyContainsModelException ignored) {
            executor.sendMessage(LangConfiguration.ALREADY_IN_QUEUE.replace("%queue_name%", target.getIdentifier()));
        }
    }
}