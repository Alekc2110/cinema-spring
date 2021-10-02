package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.dto.UserDto;
import com.my.booking.cinema.service.TicketService;
import com.my.booking.cinema.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@AllArgsConstructor
@Slf4j
@Controller
public class MainController {

    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping({"", "/", "/home", "/cinema"})
    public String homePage() {
        return "home_page";
    }

    @GetMapping("/profile")
    public String profilePage(Principal principal, Model model) {
        final UserDto userByName = userService.findUserByName(principal.getName());
        log.info("return userAccount page in MainController by user name: " + userByName);
        model.addAttribute("user", userByName);
        return "user/userAccount";
    }

}
