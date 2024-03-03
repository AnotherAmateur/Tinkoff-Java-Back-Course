package edu.java.bot;

import edu.java.bot.configuration.ApplicationConfig;
import edu.java.bot.telegrambot.Bot;
import edu.java.bot.telegrambot.NotifierTelegramBot;
import edu.java.bot.usermessageprocessor.DefaultUsrMsgProcessor;
import edu.java.bot.usermessageprocessor.UserMessageProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        var context = SpringApplication.run(BotApplication.class, args);
        var appConfig = context.getBean(ApplicationConfig.class);
        UserMessageProcessor usrMsgProcessor = new DefaultUsrMsgProcessor();

        try (Bot telegramBot = new NotifierTelegramBot(appConfig, usrMsgProcessor)) {
            telegramBot.start();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }
}
