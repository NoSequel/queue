package io.github.nosequel.queue.bukkit.command.adapters;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;

public class QueueModelTypeAdapter implements TypeAdapter<QueueModel> {

    private final QueueHandler queueHandler = QueueBootstrap.getBootstrap().getPlatform().getQueueHandler();

    @Override
    public QueueModel convert(CommandExecutor commandExecutor, String s) throws Exception {
        return this.queueHandler.find(s).orElseThrow(() -> new NullPointerException("Unable to find queue"));
    }
}
