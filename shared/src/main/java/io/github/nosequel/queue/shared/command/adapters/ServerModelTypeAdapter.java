package io.github.nosequel.queue.shared.command.adapters;

import io.github.nosequel.command.adapter.TypeAdapter;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.server.ServerHandler;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServerModelTypeAdapter implements TypeAdapter<ServerModel> {

    private final ServerHandler serverHandler;

    @Override
    public ServerModel convert(CommandExecutor commandExecutor, String s) throws Exception {
        return this.serverHandler.find(s).orElseThrow(() -> new NullPointerException("Unable to find server"));
    }
}