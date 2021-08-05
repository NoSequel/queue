package io.github.nosequel.queue.shared.command.adapters;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.command.metadata.QueueMetadataAction;

public class QueueMetadataActionTypeAdapter implements TypeAdapter<QueueMetadataAction> {

    @Override
    public QueueMetadataAction convert(CommandExecutor commandExecutor, String s) throws Exception {
        return QueueMetadataAction.valueOf(s.toUpperCase());
    }
}
