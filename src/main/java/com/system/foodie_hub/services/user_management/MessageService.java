package com.system.foodie_hub.services.user_management;

import com.system.foodie_hub.entity.user_management.Message;
import com.system.foodie_hub.pojo.user_management.MessagePojo;

import java.io.IOException;
import java.util.List;

public interface MessageService {
    MessagePojo save(MessagePojo messagePojo) throws IOException;
    List<Message> fetchAll();
}
