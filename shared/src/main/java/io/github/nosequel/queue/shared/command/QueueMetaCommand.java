package io.github.nosequel.queue.shared.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.executor.CommandExecutor;
import io.github.nosequel.queue.shared.config.LangConfiguration;
import io.github.nosequel.queue.shared.config.command.QueueSubCommand;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import io.github.nosequel.queue.shared.model.server.ServerModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QueueMetaCommand {

    private final QueueHandler queueHandler;

    @Command(label = "queuemeta", aliases = {"meta"}, permission = "queue.bukkit.meta", userOnly = false)
    @Subcommand(label = "help", parentLabel = "queuemeta", permission = "queue.bukkit.meta", userOnly = false)
    public void execute(CommandExecutor executor) {
        executor.sendMessage(LangConfiguration.QUEUE_HELP_MESSAGE_HEADER);

        for (QueueSubCommand subcommand : LangConfiguration.QUEUE_SUB_COMMANDS) {
            executor.sendMessage(LangConfiguration.QUEUE_HELP_COMMAND_FORMAT
                    .replace("%sub_label%", subcommand.getSubcommandName())
                    .replace("%sub_arguments%", subcommand.getArguments())
                    .replace("%description%", subcommand.getDescription()));
        }
    }

    @Subcommand(label = "create", parentLabel = "queuemeta", permission = "queue.bukkit.meta.create", userOnly = false)
    public void create(CommandExecutor executor, String name) {
        if (queueHandler.find(name).isPresent()) {
            executor.sendMessage("&cA queue with that name already exists, please try again.");
            return;
        }

        final QueueModel model = new QueueModel(name);

        executor.sendMessage(LangConfiguration.QUEUE_CREATE_RESULT
                .replace("%queue_name%", model.getIdentifier())
        );

        queueHandler.addModel(model);
    }

    @Subcommand(label = "list", parentLabel = "queuemeta", permission = "queue.bukkit.meta.list", userOnly = false)
    public void list(CommandExecutor executor) {
        executor.sendMessage(LangConfiguration.QUEUE_LIST_MESSAGE_HEADER);

        for (QueueModel model : queueHandler.getModels()) {
            executor.sendMessage(LangConfiguration.QUEUE_LIST_MESSAGE_ENTRY
                    .replace("%queue_name%", model.getIdentifier())
                    .replace("%target_server%", model.getTargetServer() == null ? "None" : model.getTargetServer().getServerName())
            );
        }
    }

    @Subcommand(label = "setserver", parentLabel = "queuemeta", permission = "queue.bukkit.meta.set.server", userOnly = false)
    public void setServer(CommandExecutor executor, QueueModel queueModel, ServerModel serverModel) {
        queueModel.setTargetServer(serverModel);

        executor.sendMessage(LangConfiguration.QUEUE_UPDATE_SERVER
                .replace("%target_server%", serverModel.getServerName())
                .replace("%queue_name%", queueModel.getIdentifier())
        );
    }
}