package io.github.nosequel.queue.shared.update.queue;

import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.update.sync.DataSyncHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueueDataSyncHandler extends DataSyncHandler<QueueUpdateData> {

    private final QueueHandler queueHandler;

    /**
     * Handle an incoming {@link QueueUpdateData} object
     *
     * @param object the object to handle
     */
    @Override
    public void handleData(QueueUpdateData object) {
        final QueueModel queueModel = this.queueHandler
                .find(object.getModelId())
                .orElseGet(() -> new QueueModel(object.getModelId()));

        if (!object.isEqualTo(queueModel)) {
            object.updateTo(queueModel);
        }

        if (!this.queueHandler.hasModel(queueModel)) {
            this.queueHandler.addModel(queueModel);
        }
    }

    /**
     * Get the type of the data sync handler
     *
     * @return the type
     */
    @Override
    public Class<QueueUpdateData> getType() {
        return QueueUpdateData.class;
    }
}
