package io.github.nosequel.queue.shared.update.server;

import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import io.github.nosequel.queue.shared.update.SyncHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class LocalServerUpdateTask extends Thread {

    private final ServerHandler serverHandler;
    private final SyncHandler syncHandler;

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
            Thread.sleep(2500L);

            final ServerModel model = this.serverHandler.getLocalServer();
            final ServerUpdateData updateData = model.getPreviousUpdateData();

            model.updateToLocal(this.serverHandler.getProvider());

            if (updateData == null || !updateData.isEqualTo(model)) {
                final ServerUpdateData data = new ServerUpdateData(model);

                this.syncHandler.pushData(data);
                model.setPreviousUpdateData(data);
            }

            Thread.sleep(2500L);
        }
    }
}