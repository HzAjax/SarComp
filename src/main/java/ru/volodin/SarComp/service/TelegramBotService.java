package ru.volodin.SarComp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.springframework.beans.factory.annotation.Value;
import ru.volodin.SarComp.entity.Lead;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TelegramBotService {

    @Value("${bot.chatId}")
    private String BOT_CHAT_ID;

    @Autowired
    protected TelegramClient telegramClient;

    public String sendNotificationTelegram(Lead lead){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd HH:mm:ss"); //TODO: проверить паттерн?
        String formattedDate = now.format(formatter);
        String message =
                "ЗАКАЗ:\n" +
                        "Клиент: " + lead.getName() + "\n" +
                        "Компьютер: " + lead.getComp().getName() + "\n" +
                        "Срок: " + lead.getDay_count() + "\n" +
                        "Контакт: " + lead.getPhone() + "\n" +
                        "Время заказа: " + formattedDate;
        try {
            SendMessage messageBuilder = SendMessage.builder()
                    .chatId(Long.parseLong(BOT_CHAT_ID))
                    .text(message)
                    .build();
            telegramClient.execute(messageBuilder);
        } catch (TelegramApiException e) {
            return e.getMessage();
        }
        return message;
    }

}
