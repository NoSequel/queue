package io.github.nosequel.queue.shared.model.server;

import io.github.nosequel.queue.shared.cache.ModelCache;
import io.github.nosequel.queue.shared.update.SyncHandler;
import io.github.nosequel.queue.shared.update.server.LocalServerUpdateTask;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerHandler extends ModelCache<String, ServerModel> {

    private final ServerModel localServer;
    private final SyncHandler syncHandler;

    private ServerProvider provider;

    /**
     * Constructor to make a new {@link ServerHandler} object
     *
     * @param localServer the local server
     * @param syncHandler the handler for synchronization
     */
    public ServerHandler(ServerModel localServer, SyncHandler syncHandler) {
        this.localServer = localServer;
        this.syncHandler = syncHandler;

        new LocalServerUpdateTask(this, syncHandler).start();
    }
}