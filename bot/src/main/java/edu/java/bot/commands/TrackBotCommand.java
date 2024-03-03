package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class TrackBotCommand implements BotCommand {
    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "start tracking updates";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "Tracking");
    }
}
