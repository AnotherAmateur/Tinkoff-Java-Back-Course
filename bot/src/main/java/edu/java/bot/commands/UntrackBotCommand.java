package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class UntrackBotCommand implements BotCommand {
    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "stop tracking updates";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "Stopped tracking");
    }
}
