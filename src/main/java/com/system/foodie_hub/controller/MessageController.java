package com.system.foodie_hub.controller;

import com.system.foodie_hub.entity.user_management.Message;
import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.MessagePojo;
import com.system.foodie_hub.services.user_management.MessageService;
import com.system.foodie_hub.services.user_management.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;
    @PostMapping("/sendMessage")
    public String sendMessage(@Valid MessagePojo messagePojo,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException{
        System.out.println(messagePojo.getContent());
        messagePojo.setSender(getCurrentUser()); //uncomment this after debugging code
//        messagePojo.setSender("hero@gmail.com");
        messagePojo.setTime(getCurrentTime());
//        messagePojo.setEmail(getCurrentUser()); uncomment this after debugging code
        messagePojo.setEmail(getCurrentUser());
        messageService.save(messagePojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/global.html";
    }



    @PostMapping("/getMessage")
    public void get(@RequestBody MessagePojo messagePojo) throws IOException {
        messageService.save(messagePojo);
    }

    public void updateSenderName(){
        String senderName = getUser(getCurrentUser()).getFullName();
        messageService.updateSender(getCurrentUser(), senderName);
    }
    public String CurrentUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        model.addAttribute("currentuser", currentUsername);
        return "Current user: " + currentUsername;
    }
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return currentUsername;
    }

    public String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        return hour + ":" + minute;
    }

@GetMapping("/allmessage")
    public String getAllMessage(Model model){
        List<Message> messages = messageService.fetchAll();
        model.addAttribute("messageList", messages.stream().map(message ->
                Message.builder()
                        .id(message.getId())
//                        .imageBase64(getImageBase64(user.getImage()))
                        .email(message.getEmail())
                        .sender(message.getSender())
                        .content(message.getContent())
                        .time(message.getTime())
                        .build()

        ));

//        model.addAttribute("UPLOAD_DIRECTORY", "/" + UPLOAD_DIRECTORY);

        return "newglobal";
    }

    @GetMapping("allmsg")
    public String getMsg(Model model){
        List<Message> mesg = messageService.fetchAll();
        model.addAttribute("msg",mesg);
        return "newglobal";
    }

    public User getUser(String email) {
        User u = userService.fetchByEmail(email);
        return u;
    }
}
