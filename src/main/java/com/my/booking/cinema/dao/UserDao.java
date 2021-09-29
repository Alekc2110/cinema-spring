package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.User;


public interface UserDao {

    User findByUserName(String username);

    boolean existsUserByEmail (String email);

    User save(User user);
}
