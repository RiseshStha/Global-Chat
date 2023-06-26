package com.system.foodie_hub.controller;

import com.pusher.rest.Pusher;
import com.system.foodie_hub.dto.MessageDto;
import com.system.foodie_hub.utils.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;


@Controller
public class ChatController {
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public OutputMessage send(MessageDto message) throws Exception {
//        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        return new OutputMessage(message.getFrom(), message.getText(), time);
//    }
//
    @GetMapping("/chatPage")
    public String getChatPage(){
        return "chatPage.html";
    }

    @PostMapping("/trigger")
    public String triggerEvent(MessageDto messageDto){
        Pusher pusher = new Pusher("1624714", "07702d38cfb7823f21b8", "93fd1f507115d9300f6d");
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);

        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", messageDto.getText()));
        return "chatPage.html";
    }
}
