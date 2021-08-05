package io.github.nosequel.queue.shared.command.adapters;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.queue.QueueModelMetadata;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QueueModelMetadataTypeAdapter implements TypeAdapter<QueueModelMetadata> {

    @Override
    public QueueModelMetadata convert(CommandExecutor commandExecutor, String s) throws Exception {
        return QueueModelMetadata.valueOf(s.toUpperCase());
    }

    @Override
    public void handleException(CommandExecutor executor, String source, Exception exception) {
        executor.sendMessage("&cInvalid Option \"" + source + "\", try: " + Arrays.stream(QueueModelMetadata.values())
                .map(QueueModelMetadata::name)
                .collect(Collectors.joining(", "))
        );
    }
}