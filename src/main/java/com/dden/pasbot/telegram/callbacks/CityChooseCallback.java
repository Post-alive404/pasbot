package com.dden.pasbot.telegram.callbacks;

import com.dden.pasbot.data.ChatCitiesRepository;
import com.dden.pasbot.data.ChatsCitiesEntity;
import com.dden.pasbot.data.CityRepository;
import com.dden.pasbot.data.DepartmentsRepository;
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
public class CityChooseCallback implements CallbackHandler{

    private final ChatCitiesRepository chatCitiesRepository;

    private final CityRepository cityRepository;

    private final DepartmentsRepository departmentsRepository;

    @Override
    public SendMessage apply(Callback callback, Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer cityId = Integer.valueOf(callback.getData());
        chatCitiesRepository.merge(new ChatsCitiesEntity(chatId, cityId));
        SendMessage answer = new SendMessage(String.valueOf(chatId), String.format(Consts.CHOSE_MESSAGE, cityRepository.getName(cityId)));
        addTypesKeyboard(answer, cityId);
        return answer;
    }

    private void addTypesKeyboard(SendMessage answer, Integer cityId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        for (var type : departmentsRepository.getTypesOfCity(cityId)) {
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(type.name);
            String jsonCallback = JsonHandler.toJson(List.of(CallbackType.TYPE_CHOOSE, type));
            inlineKeyboardButton.setCallbackData(jsonCallback);
            keyboardButtonsRow.add(inlineKeyboardButton);
        }
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        answer.setReplyMarkup(inlineKeyboardMarkup);
    }
}
