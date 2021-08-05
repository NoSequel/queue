package io.github.nosequel.queue.shared.model.queue.update.metadata;

import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.update.sync.DataSyncHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueueMetadataDataSyncHandler extends DataSyncHandler<QueueMetadataData> {

    private final QueueHandler queueHandler;

    /**
     * Handle an incoming {@link QueueMetadataData} object
     *
     * @param object the object to handle
     */
    @Override
    public void handleData(QueueMetadataData object) {
        this.queueHandler
                .find(object.getIdentifier())
                .ifPresent(model -> {
                    switch (object.getAction()) {
                        case ADD: {
                            model.addMetadata(object.getMetadata());
                        }
                        break;

                        case REMOVE: {
                            model.removeMetadata(object.getMetadata());
                        }
                        break;
                    }
                });
    }

    /**
     * Get the type of the data sync handler
     *
     * @return the type
     */
    @Override
    public Class<QueueMetadataData> getType() {
        return QueueMetadataData.class;
    }
}
