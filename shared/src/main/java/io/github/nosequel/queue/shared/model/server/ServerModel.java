package io.github.nosequel.queue.shared.model.server;

import io.github.nosequel.queue.shared.model.Model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class ServerModel implements Model<String> {

    private final String serverName;

    private int onlinePlayers;
    private int maxPlayers;

    private final Set<ServerMetadata> metadatum = new HashSet<>();
    private final Set<UUID> whitelistedPlayers = new HashSet<>();

    /**
     * Add an array of {@link ServerMetadata} to the {@link ServerModel#metadatum} field
     *
     * @param metadatum the metadata to add
     */
    public void addMetadata(ServerMetadata... metadatum) {
        this.metadatum.addAll(Arrays.asList(metadatum));
    }

    /**
     * Check if the {@link ServerModel#metadatum} field contains the
     * provided {@link ServerMetadata} metadata value.
     *
     * @param metadata the metadata value to check for
     * @return whether it has the metadata or not
     */
    public boolean hasMetadata(ServerMetadata metadata) {
        return this.metadatum.contains(metadata);
    }

    /**
     * Check if the model's identifier equals to the provided identifiers
     *
     * @param value the identifier to compare it to
     * @return whether it equals to the identifier or not
     */
    @Override
    public boolean equalsToId(String value) {
        return this.serverName.equalsIgnoreCase(value);
    }
}