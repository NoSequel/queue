package io.github.nosequel.queue.shared.model.server;

import java.util.Set;
import java.util.UUID;

public interface ServerProvider {

    /**
     * Get the amount of local online players
     *
     * @return the amount of local online players
     */
    int getLocalOnlinePlayers();

    /**
     * Get the maximum amount of players locally
     *
     * @return the maximum amount of players
     */
    int getLocalMaxPlayers();

    /**
     * Get the local status of the server
     *
     * @return the status of the current server
     */
    ServerStatus getLocalStatus();

    /**
     * Get the whitelisted players locally
     *
     * @return the whitelisted players
     */
    Set<UUID> getLocalWhitelistedPlayers();

}
