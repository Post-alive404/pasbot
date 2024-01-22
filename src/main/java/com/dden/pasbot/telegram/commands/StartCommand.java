package com.dden.pasbot.telegram.commands;

import com.dden.pasbot.data.CityEntity;
import com.dden.pasbot.data.CityRepository;
import com.dden.pasbot.telegram.callbacks.CallbackType;
import com.dden.pasbot.utils.Consts;
import com.dden.pasbot.utils.JsonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StartCommand implements Command{

    private final CityRepository repository;
    @Override
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(Consts.START_MESSAGE);

        List<CityEntity> allCities = repository.findAll();

        addKeyboard(sendMessage, allCities);
        return sendMessage;
    }

    private void addKeyboard(SendMessage sendMessage, List<CityEntity> allCities) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtons = new ArrayList<>();
        for (var city : allCities) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(city.getName());
            String jsonCallback = JsonHandler.toJson(List.of(CallbackType.CITY_CHOOSE, city.getId().toString()));
            inlineKeyboardButton.setCallbackData(jsonCallback);
            keyboardButtons.add(inlineKeyboardButton);
        }
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtons);
        inlineKeyboardMarkup.setKeyboard(rowList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }
}
