package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.UserDao;
import com.my.booking.cinema.model.User;
import com.my.booking.cinema.model.enums.Role;
import com.my.booking.cinema.model.web.SignupRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {

    private UserDao userDao;
    private  PasswordEncoder encoder;
    private  ModelMapper mapper;

    public UserService(UserDao userDao, PasswordEncoder encoder, ModelMapper mapper) {
        this.userDao = userDao;

        this.encoder = encoder;
        this.mapper = mapper;
    }

    public User findUserByName(String name) {
        return userDao.findByUserName(name);
    }

    public boolean saveUser(SignupRequest userCreate) {
        log.info("save new user : " + userCreate );
        if (userDao.existsUserByEmail(userCreate.getEmail())) {
            return false;
        }
       final User user = User.builder().
               email(userCreate.getEmail()).
               name(userCreate.getName()).
               password(encoder.encode(userCreate.getPassword())).
               roles(List.of(Role.USER)).build();

        User saved = userDao.save(user);
        log.info("saved new user with id: " + user.getId());
        return saved != null;
    }



}
