package io.github.nosequel.queue.bukkit.config;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.annotation.Configurable;
import io.github.nosequel.config.bukkit.BukkitConfigurationFile;
import io.github.nosequel.queue.bukkit.command.QueueMetaCommand;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LangConfiguration extends Configuration {

    @Configurable(path = "messages.queue.help.header")
    public static String QUEUE_HELP_MESSAGE_HEADER = "&6=== &eViewing usages of &6/queuemeta ===";

    @Configurable(path = "messages.queue.help.format")
    public static String QUEUE_HELP_COMMAND_FORMAT = "&equeuemeta %sub_label% &6%sub_arguments% - %description%";

    @Configurable(path = "messages.queue.help.sub_commands")
    public static QueueMetaCommand.QueueSubcommand[] QUEUE_SUB_COMMANDS = new QueueMetaCommand.QueueSubcommand[] {
            new QueueMetaCommand.QueueSubcommand("help", "", "Show the message you're viewing now."),
            new QueueMetaCommand.QueueSubcommand("list", "", "Display all the registered queues."),
            new QueueMetaCommand.QueueSubcommand("create", "<name>", "Create a new queue for the current server.")
    };

    @Configurable(path = "messages.queue.created")
    public static String QUEUE_CREATE_RESULT = "&eSuccessfully created a queue with the name &d%queue_name%";

    @Configurable(path = "messages.queue.info.header")
    public static String QUEUE_LIST_MESSAGE_HEADER = "&6=== &eViewing all registered queues &6===";

    @Configurable(path = "messages.queue.info.entry")
    public static String QUEUE_LIST_MESSAGE_ENTRY = "&e%queue_name% &7| &f%target_server%";

    @Configurable(path = "messages.queue.server.update")
    public static String QUEUE_UPDATE_SERVER = "&eYou have set &d%queue_name%&e's target server to &d%target_server%";

    @SneakyThrows
    public LangConfiguration(File file) {
        super(new BukkitConfigurationFile(
                file,
                YamlConfiguration.loadConfiguration(file)
        ));

        this.load();
        this.save();
    }
}