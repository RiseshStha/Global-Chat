package com.system.foodie_hub.controller;


import com.system.foodie_hub.dto.AuthenticationResponse;
import com.system.foodie_hub.dto.LoginDto;
import com.system.foodie_hub.pojo.user_management.UserPojo;
import com.system.foodie_hub.services.user_management.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String getPage(Model model) {
        model.addAttribute("user", new UserPojo());
        return "login";
    }


    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new UserPojo());
        return "register";
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute UserPojo userPojo) throws IOException {
        System.out.println(userPojo);
        userService.save(userPojo);
        return "/login";
    }


}
