package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.web.LoginRequest;
import com.my.booking.cinema.model.web.SignupRequest;
import com.my.booking.cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class LoginRegistrationController {

    private final UserService userService;

    public LoginRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("userForm") LoginRequest userForm) {
        return "login";
    }


    @PostMapping("/login")
    public String loginPostPage(@ModelAttribute("userForm") LoginRequest userForm) {
    return "redirect:/profile";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("regForm") SignupRequest regForm) {
        return "common/registerUser";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("regForm") @Valid SignupRequest regForm, BindingResult bindingResult, Model model) {
        log.info("try to add new user in registration controller: " + regForm);
        if (bindingResult.hasErrors()) {
            return "common/registerUser";
        }
        if (!regForm.getPassword().equals(regForm.getConfirmPassword())){
            model.addAttribute("passwordError", "passwords not matches");
            return "common/registerUser";
        }
        if (!userService.saveUser(regForm)){
            model.addAttribute("usernameError", "User with this email already exists");
            return "common/registerUser";
        }

        return "redirect:/";
    }

}
