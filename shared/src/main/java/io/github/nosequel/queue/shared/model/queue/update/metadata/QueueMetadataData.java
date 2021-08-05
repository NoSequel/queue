package io.github.nosequel.queue.shared.model.queue.update.metadata;

import io.github.nosequel.queue.shared.command.metadata.QueueMetadataAction;
import io.github.nosequel.queue.shared.model.queue.QueueModelMetadata;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QueueMetadataData {

    private final QueueModelMetadata metadata;
    private final QueueMetadataAction action;
    private final String identifier;

}
