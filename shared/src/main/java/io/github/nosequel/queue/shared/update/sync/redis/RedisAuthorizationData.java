package io.github.nosequel.queue.shared.update.sync.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RedisAuthorizationData {

    private final String hostname;
    private final int port;

    private final String password;

    /**
     * Check if redis should authorize the login
     *
     * @return whether it should authorize or not
     */
    public boolean shouldAuthorize() {
        return !this.password.isEmpty();
    }
}