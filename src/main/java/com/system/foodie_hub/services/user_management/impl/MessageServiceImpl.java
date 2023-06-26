package com.system.foodie_hub.services.user_management.impl;

import com.system.foodie_hub.entity.user_management.Message;
import com.system.foodie_hub.pojo.user_management.MessagePojo;
import com.system.foodie_hub.repo.user_management.MessageRepo;
import com.system.foodie_hub.services.user_management.MessageService;
import com.system.foodie_hub.services.user_management.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    @Override
    public MessagePojo save(MessagePojo messagePojo) throws IOException {
        Message message = new Message();
        message.setId(messagePojo.getId());
        message.setTime(messagePojo.getTime());
        message.setSender(messagePojo.getSender());
        message.setContent(messagePojo.getContent());
        messageRepo.save(message);
        return messagePojo;
    }
}
