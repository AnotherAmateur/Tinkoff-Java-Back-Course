package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class ListBotCommand implements BotCommand {
    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "show a list of tracked links";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "list of links");
    }
}
