package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.usermessageprocessor.UserMessageProcessor;
import java.util.List;
import java.util.stream.Collectors;

public class HelpBotCommand implements BotCommand {
    private final UserMessageProcessor usrMsgProcessor;

    public HelpBotCommand(UserMessageProcessor usrMsgProcessor) {
        this.usrMsgProcessor = usrMsgProcessor;
    }

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "get available commands";
    }

    @Override
    public SendMessage handle(Update update) {
        List<? extends BotCommand> availableCommands = usrMsgProcessor.commands();
        String response = availableCommands.stream()
            .map(command -> command.command() + " - " + command.description())
            .collect(Collectors.joining("\n"));

        return new SendMessage(update.message().chat().id(), response);
    }
}
