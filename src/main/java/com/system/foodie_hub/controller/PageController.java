package com.system.foodie_hub.controller;

import com.system.foodie_hub.entity.user_management.Message;
import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.MessagePojo;
import com.system.foodie_hub.pojo.user_management.UserPojo;
import com.system.foodie_hub.services.user_management.MessageService;
import com.system.foodie_hub.services.user_management.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import static java.util.logging.Logger.global;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PageController {
    private final MessageService messageService;
    private final UserService userService;

    @GetMapping ("profile")
    public String getProfilePage(Model model){
        User users = userService.fetchById(getUser(getCurrentUser()).getId());
//        model.addAttribute("user",getCurrentUser());
        model.addAttribute("user",getUser(getCurrentUser()));
        model.addAttribute("obj", new UserPojo(users));
        model.addAttribute("userDetail", getUserId());
        model.addAttribute("userList", User.builder().imageBase64(getImageBase64(users.getImage())).build());
        return "Profile";
    }
    public User getUserId(){
        User user = getUser(getCurrentUser());
        return user;
    }
//    @GetMapping ("newProfile")
//    public String getProfilePage2(Model model){
////        model.addAttribute("user",getCurrentUser());
//        model.addAttribute("user",getUser(getCurrentUser()));
//        return "newProfile";
//    }

    @GetMapping ("globalchat")
    public String getGlobalPage(Model model){
        List<Message> messages= messageService.fetchAll();
        model.addAttribute("messages", messages);
//        model.addAttribute("currentuser",getCurrentUser());
        model.addAttribute("currentuser",getCurrentUser());
        return "global";
    }

    @GetMapping ("global2")
    public String getGlobalPage2(Model model){
        List<Message> messages= messageService.fetchAll();
        model.addAttribute("messages", messages);
        return "newglobal2";
    }

    @GetMapping ("aboutus")
    public String getAboutUsPage(){
        return "AboutUs";
    }

//    @GetMapping ("/logout")
//    public String logout(Authentication authentication) {
//        if (authentication.isAuthenticated()) {
//            SecurityContextHolder.clearContext();
//        }
//        return "redirect:/login";
//    }
    @GetMapping ("newglobal")
    public String getNewGlobalPage(Model model){
        model.addAttribute("message", new MessagePojo());
        return "newglobal";
    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        return currentUsername;
    }
    public User getUser(String email) {
        User u = userService.fetchByEmail(email);
        return u;
    }
    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/file_server/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }
//    public String getCurrentUserEmail() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUseremail= authentication.getEmail();
//        return currentUseremail;
//    }
//
//    @PostMapping("/global")
//    public String getGlobalPage(){
//        return "global";
//    }
}
