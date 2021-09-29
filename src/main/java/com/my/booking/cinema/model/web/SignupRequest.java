package com.my.booking.cinema.model.web;


import com.my.booking.cinema.lib.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SignupRequest {
    @NotEmpty(message = "min 3 max 20 letters")
    @Size(min = 3, max = 20)
    private String name;
    @NotEmpty(message = "password should not be empty")
    @Password
    private String password;
    @NotEmpty(message = "password should not be empty")
    @Password
    private String confirmPassword;
    @NotEmpty(message = "should not be empty")
    @Email(message = "wrong email input")
    private String email;
}
