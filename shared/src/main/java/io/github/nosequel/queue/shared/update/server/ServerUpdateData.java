package io.github.nosequel.queue.shared.update.server;

import io.github.nosequel.queue.shared.model.server.ServerMetadata;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import io.github.nosequel.queue.shared.model.server.ServerStatus;
import io.github.nosequel.queue.shared.update.UpdateData;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

@Getter
public class ServerUpdateData extends UpdateData<ServerModel> {

    private final String serverName;

    private final int onlinePlayers;
    private final int maxPlayers;

    private final ServerStatus status;

    private final Set<UUID> whitelistedPlayers;
    private final Set<ServerMetadata> metadatum;

    /**
     * Constructor to make a new {@link ServerUpdateData} object.
     * <p>
     * All The fields get set from the data within the
     * provided {@link ServerModel} object.
     * </p>
     *
     * @param model the model to get all the data from
     */
    public ServerUpdateData(ServerModel model) {
        this.serverName = model.getServerName();

        this.onlinePlayers = model.getOnlinePlayers();
        this.maxPlayers = model.getMaxPlayers();

        this.status = model.getStatus();
        this.whitelistedPlayers = model.getWhitelistedPlayers();
        this.metadatum = model.getMetadatum();
    }

    /**
     * Check if the update data is equal to a
     * provided {@link ServerModel} object.
     *
     * @param data the data to match against the update data
     * @return whether it's equal to the update data or not
     */
    @Override
    public boolean isEqualTo(ServerModel data) {
        return data.getServerName().equals(this.serverName)
                && data.getOnlinePlayers() == this.onlinePlayers
                && data.getMaxPlayers() == this.maxPlayers
                && data.getMetadatum().containsAll(this.metadatum) && this.metadatum.containsAll(data.getMetadatum())
                && data.getStatus().equals(this.status)
                && data.getWhitelistedPlayers().containsAll(whitelistedPlayers) && whitelistedPlayers.containsAll(data.getWhitelistedPlayers());
    }

    /**
     * Update the provided {@link ServerModel} object to
     * the current update data object.
     *
     * @param object the object to update it to
     */
    @Override
    public void updateTo(ServerModel object) {
        object.setMaxPlayers(this.maxPlayers);
        object.setOnlinePlayers(this.onlinePlayers);
        object.setStatus(this.status);

        // update the metadatum of the server
        object.getMetadatum().clear();
        object.getMetadatum().addAll(this.metadatum);

        // update the whitelisted players of the server
        object.getWhitelistedPlayers().clear();
        object.getWhitelistedPlayers().addAll(this.whitelistedPlayers);
    }
}