package com.system.foodie_hub.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.util.logging.Logger.global;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class PageController {
//    @GetMapping
//    public String getPage(){
//        return "global";
//    }

    @GetMapping ("profile")
    public String getProfilePage(){
        return "Profile";
    }

    @GetMapping ("globalchat")
    public String getGlobalPage(){
        return "global";
    }

    @GetMapping ("aboutus")
    public String getAboutUsPage(){
        return "AboutUs";
    }

    @GetMapping ("/logout")
    public String logout(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }
//
//    @PostMapping("/global")
//    public String getGlobalPage(){
//        return "global";
//    }
}
