package com.system.foodie_hub.services.user_management;

import com.system.foodie_hub.pojo.user_management.MessagePojo;

import java.io.IOException;

public interface MessageService {
    MessagePojo save(MessagePojo messagePojo) throws IOException;
}
