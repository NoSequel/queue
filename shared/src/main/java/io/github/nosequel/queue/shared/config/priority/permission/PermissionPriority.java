package io.github.nosequel.queue.shared.config.priority.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PermissionPriority {

    private final String permission;
    private final int priority;

}
