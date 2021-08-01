package io.github.nosequel.queue.shared;

import lombok.Getter;

@Getter
public class QueueBootstrap {

    private static QueueBootstrap bootstrap;
    private final QueuePlatform platform;

    /**
     * Construct a new bootstrap class
     *
     * @param platform the platform to register within the bootstrap
     */
    public QueueBootstrap(QueuePlatform platform) {
        bootstrap = this;
        this.platform = platform;
    }

    /**
     * Get the bootstrap instance
     *
     * @return the instance of the bootstrap
     */
    public static QueueBootstrap getBootstrap() {
        return bootstrap;
    }
}
