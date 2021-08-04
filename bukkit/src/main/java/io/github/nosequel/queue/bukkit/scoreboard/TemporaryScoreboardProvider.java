package io.github.nosequel.queue.bukkit.scoreboard;

import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.player.QueuePlayerHandler;
import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import io.github.nosequel.scoreboard.element.ScoreboardElementHandler;
import org.bukkit.entity.Player;

public class TemporaryScoreboardProvider implements ScoreboardElementHandler {

    private final QueuePlayerHandler playerHandler = QueueBootstrap.getBootstrap().getPlatform().getQueuePlayerHandler();
    private final QueueHandler queueHandler = QueueBootstrap.getBootstrap().getPlatform().getQueueHandler();
    private final ServerHandler serverHandler = QueueBootstrap.getBootstrap().getPlatform().getServerHandler();

    @Override
    public ScoreboardElement getElement(Player player) {
        final ScoreboardElement element = new ScoreboardElement();

        if(player != null && player.getUniqueId() != null) {
            final QueuePlayerModel playerModel = this.playerHandler
                    .find(player.getUniqueId())
                    .orElse(null);

            element.setTitle(serverHandler.getLocalServer().getServerName() + " | " + serverHandler.getLocalServer().getOnlinePlayers());
            element.add(" ");

            if (playerModel != null) {
                int i = 0;

                for (QueueModel queueModel : this.queueHandler.getQueue(playerModel)) {
                    for (String line : this.getLines(playerModel, queueModel)) {
                        i++;

                        if (i > 16) {
                            return element;
                        }

                        element.add(line);
                    }
                }
            }
        }

        return element;
    }

    private String[] getLines(QueuePlayerModel playerModel, QueueModel queueModel) {
        return new String[] {
                "queue_id: " + queueModel.getIdentifier(),
                "position: " + queueModel.getPosition(playerModel),
                ""
        };
    }

}