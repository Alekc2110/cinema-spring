package com.my.booking.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final String CORRECT_PASSWORD = "[a-zA-Z0-9]{4,20}";

    public void initialize(Password constraint) {
    }

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext context) {
        Pattern pattern  = Pattern.compile(CORRECT_PASSWORD);
        return   pattern.matcher(pass).matches();
    }
}