package io.github.nosequel.queue.bukkit.providers;

import io.github.nosequel.queue.shared.model.server.ServerProvider;
import io.github.nosequel.queue.shared.model.server.ServerStatus;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class BukkitServerProvider implements ServerProvider {

    /**
     * Get the amount of local online players
     *
     * @return the amount of local online players
     */
    @Override
    public int getLocalOnlinePlayers() {
        return Bukkit.getOnlinePlayers().size();
    }

    /**
     * Get the maximum amount of players locally
     *
     * @return the maximum amount of players
     */
    @Override
    public int getLocalMaxPlayers() {
        return Bukkit.getMaxPlayers();
    }

    /**
     * Get the local status of the server
     *
     * @return the status of the current server
     */
    @Override
    public ServerStatus getLocalStatus() {
        return ServerStatus.ONLINE;
    }

    /**
     * Get the whitelisted players locally
     *
     * @return the whitelisted players
     */
    @Override
    public Set<UUID> getLocalWhitelistedPlayers() {
        return Bukkit.getWhitelistedPlayers().stream()
                .map(OfflinePlayer::getUniqueId)
                .collect(Collectors.toSet());
    }
}