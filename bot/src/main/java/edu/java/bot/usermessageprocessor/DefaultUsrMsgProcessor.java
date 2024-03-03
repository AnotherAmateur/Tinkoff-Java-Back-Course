package edu.java.bot.usermessageprocessor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.BotCommand;
import edu.java.bot.commands.HelpBotCommand;
import edu.java.bot.commands.ListBotCommand;
import edu.java.bot.commands.StartBotCommand;
import edu.java.bot.commands.TrackBotCommand;
import edu.java.bot.commands.UntrackBotCommand;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultUsrMsgProcessor implements UserMessageProcessor {
    public final List<BotCommand> botCommands;

    public DefaultUsrMsgProcessor() {
        botCommands = new ArrayList<>();

        registerCommand(new StartBotCommand());
        registerCommand(new TrackBotCommand());
        registerCommand(new HelpBotCommand(this));
        registerCommand(new UntrackBotCommand());
        registerCommand(new ListBotCommand());
    }

    @Override
    public List<? extends BotCommand> commands() {
        return botCommands;
    }

    @Override
    public Optional<SendMessage> process(Update update) {
        if (update.message() != null && update.message().text() != null) {
            var msg = update.message();

            if (msg.entities() != null && msg.entities()[0].type().name().equals("bot_command")) {
                for (BotCommand command : botCommands) {
                    if (command.supports(update)) {
                        return Optional.ofNullable(command.handle(update));
                    }
                }

                return Optional.of(new SendMessage(update.message().chat().id(), "Unknown command"));
            }
        }

        return Optional.empty();
    }

    private void registerCommand(BotCommand command) {
        botCommands.add(command);
    }
}
