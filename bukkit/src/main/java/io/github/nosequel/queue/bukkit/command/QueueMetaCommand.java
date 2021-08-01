package io.github.nosequel.queue.bukkit.command;

import io.github.nosequel.command.annotation.Command;
import io.github.nosequel.command.annotation.Subcommand;
import io.github.nosequel.command.bukkit.executor.BukkitCommandExecutor;
import io.github.nosequel.queue.bukkit.config.LangConfiguration;
import io.github.nosequel.queue.bukkit.util.ColorUtil;
import io.github.nosequel.queue.shared.QueueBootstrap;
import io.github.nosequel.queue.shared.model.queue.QueueHandler;
import io.github.nosequel.queue.shared.model.queue.QueueModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;

public class QueueMetaCommand {

    private final QueueHandler queueHandler = QueueBootstrap.getBootstrap().getPlatform().getQueueHandler();

    @Command(label = "queuemeta", aliases = {"meta"}, permission = "queue.bukkit.meta", userOnly = false)
    @Subcommand(label = "help", parentLabel = "queuemeta", permission = "queue.bukkit.meta", userOnly = false)
    public void execute(BukkitCommandExecutor executor) {
        executor.sendMessage(ColorUtil.translate(LangConfiguration.QUEUE_HELP_MESSAGE_HEADER));

        for (QueueSubcommand subcommand : LangConfiguration.QUEUE_SUB_COMMANDS) {
            executor.sendMessage(ColorUtil.translate(LangConfiguration.QUEUE_HELP_COMMAND_FORMAT
                    .replace("%sub_label%", subcommand.getSubcommandName())
                    .replace("%sub_arguments%", subcommand.getArguments())
                    .replace("%description%", subcommand.getDescription())));
        }
    }

    @Subcommand(label = "create", parentLabel = "queuemeta", permission = "queue.bukkit.meta.create", userOnly = false)
    public void create(BukkitCommandExecutor executor, String name) {
        if(queueHandler.find(name).isPresent()) {
            executor.sendMessage(ChatColor.RED + "A queue with that name already exists, please try again.");
            return;
        }

        final QueueModel model = new QueueModel(name);

        queueHandler.addModel(model);
    }

    @Getter
    @RequiredArgsConstructor
    public static class QueueSubcommand {
        private final String subcommandName;
        private final String arguments;

        private final String description;
    }

}
