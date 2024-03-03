package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface BotCommand {
    String command();

    String description();

    SendMessage handle(Update update);

    default boolean supports(Update update) {
        if (update.message() != null && update.message().text() != null) {
            String msg = update.message().text();
            return msg.startsWith(command());
        }
        return false;
    }
}
