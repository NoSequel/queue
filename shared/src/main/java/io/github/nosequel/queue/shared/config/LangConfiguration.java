package io.github.nosequel.queue.shared.config;

import io.github.nosequel.config.Configuration;
import io.github.nosequel.config.ConfigurationFile;
import io.github.nosequel.config.annotation.Configurable;

import io.github.nosequel.queue.shared.config.queue.command.QueueSubCommand;
import lombok.SneakyThrows;

public class LangConfiguration extends Configuration {

    @Configurable(path = "messages.queue.already_in_this_queue")
    public static String ALREADY_IN_QUEUE = "&cYou are already queueing for this server.";

    @Configurable(path = "messages.queue.join")
    public static String QUEUE_JOIN = "&6You have joined the &f%queue_name% &6queue.";

    @Configurable(path = "messages.queue.metadata.added")
    public static String QUEUE_ADD_METADATA = "&6You have &aadded &6the &f%metadata% &6metadata to the &f%queue_name% &6queue.";

    @Configurable(path = "messages.queue.metadata.REMOVED")
    public static String QUEUE_REMOVE_METADATA = "&6You have &cremoved &6the &f%metadata% &6metadata to the &f%queue_name% &6queue.";

    @Configurable(path = "messages.queue.send")
    public static String QUEUE_SEND = "&6Trying to send you to the &f%server_name% &6server.";

    @Configurable(path = "messages.queue.help.header")
    public static String QUEUE_HELP_MESSAGE_HEADER = "&6=== &eViewing usages of &6/queuemeta ===";

    @Configurable(path = "messages.queue.help.format")
    public static String QUEUE_HELP_COMMAND_FORMAT = "&equeuemeta %sub_label% &6%sub_arguments% - %description%";

    @Configurable(path = "messages.queue.help.sub_commands")
    public static QueueSubCommand[] QUEUE_SUB_COMMANDS = new QueueSubCommand[]{
            new QueueSubCommand("help", "", "Show the message you're viewing now."),
            new QueueSubCommand("list", "", "Display all the registered queues."),
            new QueueSubCommand("create", "<name>", "Create a new queue."),
            new QueueSubCommand("setserver", "<queue> <server>", "Set the target server of a queue."),
            new QueueSubCommand("metadata", "<queue> <add|remove> <metadata>", "Add or remove a provided metadata from a queue.")
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
    public LangConfiguration(ConfigurationFile file) {
        super(file);

        this.load();
        this.save();
    }
}