package io.github.nosequel.queue.shared.update.queue;

import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.update.SyncHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class QueueUpdateTask extends Thread {

    private final QueueHandler queueHandler;
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
            Thread.sleep(750);

            for (QueueModel model : this.queueHandler.getModels()) {
                if (model.getPreviousUpdateData() == null || !model.getPreviousUpdateData().isEqualTo(model)) {
                    this.syncHandler.pushData(new QueueUpdateData(
                            model
                    ));
                }
            }

            Thread.sleep(750);
        }
    }
}