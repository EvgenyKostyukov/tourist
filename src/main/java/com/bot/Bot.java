package com.bot;

import com.server.entity.City;
import com.server.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    @Autowired
    private CityService cityService;

    //бот получает соощения выполн. ф-ия
    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();   //обновл. инф. о юзере

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());


        City city = cityService.getCityByName(update.getMessage().getText());


        if (city != null) {
            sendMessage.setText(city.getInfo());
        } else {
            sendMessage.setText("Некорректный ввод!");
        }



        try {
            execute(sendMessage);
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //получение имени бота
    @Override
    public String getBotUsername() {
        return "@BestTouristBot";
    }

    //получение токена бота
    @Override
    public String getBotToken() {
        return "1529852801:AAELj5FDxkz65oHZ4c8OcvY72G5COJ5uohc";
    }
}
