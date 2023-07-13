package com.system.foodie_hub.controller;

import com.pusher.rest.Pusher;
import com.system.foodie_hub.UserDetails2;
import com.system.foodie_hub.dto.MessageDto;
import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.MessagePojo;
import com.system.foodie_hub.services.user_management.MessageService;
import com.system.foodie_hub.services.user_management.UserService;
import com.system.foodie_hub.services.user_management.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class ChatController {
    private final MessageService messageService;
    private final UserService userService;

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

//    @PostMapping("/trigger")
//    public String triggerEvent(MessageDto messageDto){
//        Pusher pusher = new Pusher("1624714", "07702d38cfb7823f21b8", "93fd1f507115d9300f6d");
//        pusher.setCluster("ap2");
//        pusher.setEncrypted(true);
//
//        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", messageDto.getText()));
//        return "chatPage.html";
//    }
@PostMapping("/trigger")
public String triggerEvent(@Valid  MessagePojo messagePojo) throws IOException {
    Pusher pusher = new Pusher("1624714", "07702d38cfb7823f21b8", "93fd1f507115d9300f6d");
    pusher.setCluster("ap2");
    pusher.setEncrypted(true);

    System.out.println(messagePojo.getContent());
//        messagePojo.setSender(getCurrentUser()); uncomment this after debugging code
    messagePojo.setSender(getUserName(getCurrentUser()));
    messagePojo.setTime(getCurrentTime());
//        messagePojo.setEmail(getCurrentUser()); uncomment this after debugging code
    messagePojo.setEmail(getCurrentUser());
    pusher.trigger("my-channel", "my-event", Collections.singletonMap("text", messagePojo));
    messageService.save(messagePojo);

    return "redirect:/globalchat";
}
@PostMapping("/trigger2")
public String triggerEvent2(@Valid  MessagePojo messagePojo) throws IOException {
    Pusher pusher = new Pusher("1624714", "07702d38cfb7823f21b8", "93fd1f507115d9300f6d");
    pusher.setCluster("ap2");
    pusher.setEncrypted(true);

    pusher.trigger("my-channel", "my-event", Collections.singletonMap("text", messagePojo.getContent()));
    System.out.println(messagePojo.getContent());
//        messagePojo.setSender(getCurrentUser()); uncomment this after debugging code
    messagePojo.setSender(getCurrentUser());
    messagePojo.setTime(getCurrentTime());
//        messagePojo.setEmail(getCurrentUser()); uncomment this after debugging code
    messagePojo.setEmail(getCurrentUser());
    messageService.save(messagePojo);

    return "redirect:/globalchat";
}

    public String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        return hour + ":" + minute;
    }
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return currentUsername;
    }
    public String getUserName(String email) {
        User u = userService.fetchByEmail(email);
        return u.getFullName();
    }
//    public String getCurrentUserEmail() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUseremail= authentication.getDetails();
//        return currentUseremail;
//    }



}
