package com.dden.pasbot.telegram.commands;

import com.dden.pasbot.data.ChatsPinsEntity;
import com.dden.pasbot.data.ChatsPinsRepository;
import com.dden.pasbot.data.DepartmentsRepository;
import com.dden.pasbot.utils.Consts;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class PinCommand implements Command{

    private final ChatsPinsRepository chatsPinsRepository;

    private final DepartmentsRepository departmentsRepository;
    @Override
    @Transactional
    public SendMessage apply(Update update) {
        long chatId = update.getMessage().getChatId();
        long userId = update.getMessage().getFrom().getId();
        Integer departmentId = chatsPinsRepository.findByChatId(chatId);
        String pin = update.getMessage().getText().split(" ")[1];

        SendMessage sendMessage = new SendMessage();;
        sendMessage.setChatId(String.valueOf(chatId));
        if (departmentId == null){
            sendMessage.setText(Consts.PIN_WRONG_ORDER);
        } else {
            sendMessage.setText(Consts.PIN_ADD_MSG);
            departmentsRepository.updatePin(departmentId, pin, userId);
            chatsPinsRepository.deleteByChatId(chatId);
        }

        return sendMessage;
    }
}
