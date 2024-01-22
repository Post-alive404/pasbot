package com.dden.pasbot.telegram.callbacks;

import com.dden.pasbot.data.ChatsPinsEntity;
import com.dden.pasbot.data.ChatsPinsRepository;
import com.dden.pasbot.data.DepartmentsRepository;
import com.dden.pasbot.dto.PinState;
import com.dden.pasbot.utils.Consts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class PinActionCallback implements CallbackHandler{

    private final DepartmentsRepository departmentsRepository;

    private final ChatsPinsRepository chatsPinsRepository;


    @Override
    public SendMessage apply(Callback callback, Update update) {
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        long userId = update.getCallbackQuery().getFrom().getId();
        SendMessage answer = new SendMessage();
        Integer addressId = Integer.valueOf(callback.getData());
        if (callback.getCallbackType() == CallbackType.PIN_DONT_ADD) {
            answer = new SendMessage(String.valueOf(chatId), Consts.PIN_DONT_ADD_BYE);
        } else if (callback.getCallbackType() == CallbackType.PIN_ADD) {
            departmentsRepository.updateState(PinState.OUTDATED, addressId, userId);
            chatsPinsRepository.merge(new ChatsPinsEntity(chatId, addressId));
            answer = new SendMessage(String.valueOf(chatId), Consts.PIN_ADD_MSG);
        }

        return answer;
    }
}
