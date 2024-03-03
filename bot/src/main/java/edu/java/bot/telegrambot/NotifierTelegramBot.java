package edu.java.bot.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.usermessageprocessor.UserMessageProcessor;
import java.util.List;
import java.util.Optional;

public class NotifierTelegramBot implements Bot {
    private final TelegramBot telegramBot;
    private final UserMessageProcessor usrMsgProcessor;

    public NotifierTelegramBot(ApplicationConfig applicationConfig, UserMessageProcessor usrMsgProcessor) {
        String token = applicationConfig.telegramToken();
        telegramBot = new TelegramBot(token);
        this.usrMsgProcessor = usrMsgProcessor;
    }

    @Override
    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(BaseRequest<T, R> request) {
        telegramBot.execute(request);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            Optional<SendMessage> response = usrMsgProcessor.process(update);
            response.ifPresent(this::execute);
        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @Override
    public void start() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public void close() {
    }
}
