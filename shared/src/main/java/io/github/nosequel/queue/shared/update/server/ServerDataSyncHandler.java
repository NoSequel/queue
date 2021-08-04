package io.github.nosequel.queue.shared.update.server;

import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import io.github.nosequel.queue.shared.update.sync.DataSyncHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServerDataSyncHandler extends DataSyncHandler<ServerUpdateData> {

    private final ServerHandler serverHandler;

    /**
     * Handle an incoming {@link ServerUpdateData} object
     *
     * @param object the object to handle
     */
    @Override
    public void handleData(ServerUpdateData object) {
        final ServerModel model = this.serverHandler
                .find(object.getServerName())
                .orElseGet(() -> new ServerModel(object.getServerName()));

        if (!object.isEqualTo(model)) {
            object.updateTo(model);
        }

        if (!serverHandler.hasModel(model)) {
            this.serverHandler.addModel(model);
        }
    }

    /**
     * Get the type of the data sync handler
     *
     * @return the type
     */
    @Override
    public Class<ServerUpdateData> getType() {
        return ServerUpdateData.class;
    }
}
