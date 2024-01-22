package com.dden.pasbot.telegram.callbacks;

import com.dden.pasbot.data.DepartmentsRepository;
import com.dden.pasbot.dto.DepartmentType;
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
public class TypeChooseCallback implements CallbackHandler{
    private final DepartmentsRepository departmentsRepository;
    @Override
    public SendMessage apply(Callback callback, Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        DepartmentType departmentType = DepartmentType.valueOf(callback.getData());
        SendMessage answer = new SendMessage(String.valueOf(chatId),
                String.format(Consts.CHOSE_TYPE_MESSAGE, departmentType.name));
        addAddressesKeyboard(answer, departmentType, chatId);
        return answer;
    }

    private void addAddressesKeyboard(SendMessage answer, DepartmentType departmentType, long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (var address : departmentsRepository.getAddressOfType(departmentType, chatId)){
            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(address.toString());
            String jsonCallback = JsonHandler.toJson(List.of(CallbackType.ADDRESS_CHOOSE, address.getId()));
            inlineKeyboardButton.setCallbackData(jsonCallback);
            keyboardButtonsRow.add(inlineKeyboardButton);
            rowList.add(keyboardButtonsRow);
        }
        inlineKeyboardMarkup.setKeyboard(rowList);
        answer.setReplyMarkup(inlineKeyboardMarkup);
    }
}
