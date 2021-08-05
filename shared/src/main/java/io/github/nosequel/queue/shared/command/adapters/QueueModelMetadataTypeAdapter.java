package io.github.nosequel.queue.shared.command.adapters;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.model.queue.QueueModelMetadata;

public class QueueModelMetadataTypeAdapter implements TypeAdapter<QueueModelMetadata> {

    @Override
    public QueueModelMetadata convert(CommandExecutor commandExecutor, String s) throws Exception {
        return QueueModelMetadata.valueOf(s.toUpperCase());
    }
}
