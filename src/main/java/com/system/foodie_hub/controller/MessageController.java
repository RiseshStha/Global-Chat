package com.system.foodie_hub.controller;

import com.system.foodie_hub.pojo.user_management.MessagePojo;
import com.system.foodie_hub.pojo.user_management.UserPojo;
import com.system.foodie_hub.services.user_management.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MessageController {
    private final MessageService messageService;
    @PostMapping("/sendMessage")
    public void sendMessage(@Valid MessagePojo messagePojo,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException{
        messageService.save(messagePojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
    }
    @PostMapping("/getMessage")
    public void get(@RequestBody MessagePojo messagePojo) throws IOException {
        messageService.save(messagePojo);
    }
//    @PostMapping("/create")
//    public String createUser(@Valid UserPojo userPojo,
//                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
//
//        Map<String, String> requestError = validateRequest(bindingResult);
//        if (requestError != null) {
//            redirectAttributes.addFlashAttribute("requestError", requestError);
//            return "redirect:/login";
//        }
//
//        userService.save(userPojo);
//        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
//
//
//        return "redirect:/login";
//    }
}
