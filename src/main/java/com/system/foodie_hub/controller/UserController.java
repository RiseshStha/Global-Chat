package com.system.foodie_hub.controller;


import com.system.foodie_hub.entity.user_management.User;
import com.system.foodie_hub.pojo.user_management.UserPojo;
import com.system.foodie_hub.services.user_management.MessageService;
import com.system.foodie_hub.services.user_management.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final MessageService messageService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/file_server";

    @GetMapping
    public String getPage() {
        return "Profile";
    }


    @GetMapping("/create")
    public String getCreatePage() {
        return "user/create";
    }


    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserPojo());
        return "register";
    }

    @PostMapping("save")
    public String saveData(@Valid UserPojo userPojo,
                           BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);

            return null;
        }
        userService.save(userPojo);
        return "data saved";
    }
    @PostMapping("save2")
    public String saveData2(@Valid UserPojo userPojo,
                           BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);

            return null;
        }
        userService.update(userPojo);
        return "redirect:/profile";
    }

    @PostMapping("update2")
    public String saveData3(@Valid UserPojo userPojo,
                           BindingResult bindingResult) throws IOException {
        userService.update2(userPojo);
        updateSenderName();
        return "redirect:/profile";
    }
    public void updateSenderName(){
        String senderName = getUser(getCurrentUser()).getFullName();
        messageService.updateSender(senderName, getCurrentUser());
    }

    @PostMapping("/create")
    public String createUser(@Valid UserPojo userPojo,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/Profile";
        }

        userService.save(userPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
        return "redirect:/login";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        User user = userService.fetchById(id);
        model.addAttribute("user", new UserPojo(user));
        return "Profile";
    }


    @GetMapping("/list")
    public String getUSerList(Model model) {
        List<User> users = userService.fetchAll();

        model.addAttribute("userList", users.stream().map(user ->
                User.builder()
                        .id(user.getId())
//                        .imageBase64(getImageBase64(user.getImage()))
                        .email(user.getEmail())
                        .fullName(user.getFullName())
                        .mobileNo(user.getMobileNo())
                        .build()

        ));

//        model.addAttribute("UPLOAD_DIRECTORY", "/" + UPLOAD_DIRECTORY);

        return "user_list";
    }

    @GetMapping("/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes, Authentication authentication) {
        messageService.deleteByEmail(getCurrentUser());
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("deleteMsg", "Row delete successfully");
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }


    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }


    @GetMapping("/edit")
    public String getUserId(Model model){
        User user = getUser(getCurrentUser());
        model.addAttribute("currentUser",user);
        return "Profile";
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




}
