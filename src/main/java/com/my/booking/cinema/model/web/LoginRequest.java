package com.my.booking.cinema.model.web;


import com.my.booking.cinema.lib.Password;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String name;
    private String password;

}