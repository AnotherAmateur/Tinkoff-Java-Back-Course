package edu.java.bot.usermessageprocessor;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.commands.BotCommand;
import java.util.List;
import java.util.Optional;

public interface UserMessageProcessor {
    List<? extends BotCommand> commands();

    Optional<SendMessage> process(Update update);
}
