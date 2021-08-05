package io.github.nosequel.queue.shared.config.queue.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QueueSubCommand {
    private final String subcommandName;
    private final String arguments;

    private final String description;
}