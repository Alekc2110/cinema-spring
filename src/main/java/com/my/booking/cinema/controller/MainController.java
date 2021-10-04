package com.my.booking.cinema.controller;

import com.my.booking.cinema.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Slf4j
@Controller
public class MainController {

    private final UserService userService;

    @GetMapping({"", "/", "/home", "/cinema"})
    public String homePage() {
        return "home_page";
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
