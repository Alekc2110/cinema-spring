package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.User;
import com.my.booking.cinema.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
public class MainController {

    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/", "/home", "/cinema"})
    public String homePage() {
        return "home_page";
    }

    @GetMapping("/profile")
    public String profilePage(Principal principal, Model model) {
        final User userByName = userService.findUserByName(principal.getName());
        log.info("return userAccount page in MainController by user name: " + userByName);
        model.addAttribute("user", userByName);
        return "user/userAccount";
    }

}
