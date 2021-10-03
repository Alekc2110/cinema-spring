package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.dto.UserDto;
import com.my.booking.cinema.service.TicketService;
import com.my.booking.cinema.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@AllArgsConstructor
@Slf4j
@Controller
public class MainController {
    private static final String PERCENTAGE_BOOKED_SEATS = "percentage";
    private static final String USER = "user";

    private final UserService userService;

    @GetMapping({"", "/", "/home", "/cinema"})
    public String homePage() {
        return "home_page";
    }

    @GetMapping("/profile")
    public String profilePage(Principal principal, Model model) {
        final UserDto userByName = userService.findUserByName(principal.getName());
        log.info("return userAccount page in MainController by user name: " + userByName);
        model.addAttribute(USER, userByName);
        String percentage = (String) model.getAttribute(PERCENTAGE_BOOKED_SEATS);
        if(percentage != null)
            model.addAttribute(PERCENTAGE_BOOKED_SEATS, percentage);
        return "user/userAccount";
    }

//    @PostMapping("/profile")
//    public String profilePostPage(Principal principal, Model model) {
//        final UserDto userByName = userService.findUserByName(principal.getName());
//        log.info("return userAccount page in MainController by user name: " + userByName);
//        model.addAttribute(USER, userByName);
//        String percentage = (String) model.getAttribute(PERCENTAGE_BOOKED_SEATS);
//        if(percentage != null)
//            model.addAttribute(PERCENTAGE_BOOKED_SEATS, percentage);
//        return "user/userAccount";
//    }

}
