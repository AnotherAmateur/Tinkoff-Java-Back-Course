package edu.java.bot.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class StartBotCommand implements BotCommand {
    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "register";
    }

    @Override
    public SendMessage handle(Update update) {
        return new SendMessage(update.message().chat().id(), "Hi. I'm waiting for your instructions");
    }
}
