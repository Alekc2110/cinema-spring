package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.User;

import java.util.Optional;


public interface UserDao {

    User findByUserName(String username);

    boolean existsUserByEmail (String email);

    Optional<User> save(User user);
}
