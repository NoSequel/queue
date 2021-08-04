package io.github.nosequel.queue.shared.model.queue.update;

import io.github.nosequel.queue.shared.model.player.QueuePlayerModel;
import io.github.nosequel.queue.shared.model.player.QueuePlayerProvider;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.queue.QueueModelMetadata;
import io.github.nosequel.queue.shared.model.server.ServerMetadata;
import io.github.nosequel.queue.shared.model.server.ServerModel;

import io.github.nosequel.queue.shared.update.SyncHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class QueueMoveTask extends Thread {

    private final QueueHandler queueHandler;
    private final SyncHandler syncHandler;
    private final QueuePlayerProvider playerProvider;

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    @SneakyThrows
    public void run() {
        while (true) {
            for (QueueModel model : this.queueHandler.getModels()) {
                if (!model.hasMetadata(QueueModelMetadata.PAUSED)) {
                    final ServerModel serverModel = model.getTargetServer();

                    if (serverModel != null && serverModel.getMaxPlayers() > serverModel.getOnlinePlayers()) {
                        final QueuePlayerModel current = model.getEntries().poll();

                        if (current != null && (serverModel.getWhitelistedPlayers().contains(current.getUniqueId()) || !serverModel.hasMetadata(ServerMetadata.WHITELISTED))) {
                            this.syncHandler.pushData(new GenericQueueData(
                                    current,
                                    QueueUpdateType.LEAVE,
                                    model.getIdentifier()
                            ));

                            this.playerProvider.sendMessage(current, "&6Attempting to send you to the &f" + serverModel.getServerName() + " &6server.");
                            this.playerProvider.sendToServer(current, serverModel);
                        }
                    }
                }
            }

            Thread.sleep(2500L);
        }
    }
}