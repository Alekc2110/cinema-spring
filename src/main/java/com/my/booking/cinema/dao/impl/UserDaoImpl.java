package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.UserDao;
import com.my.booking.cinema.dao.repository.UserRepository;
import com.my.booking.cinema.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public User findByUserName(String username) {
        log.info("get user by user name: " + username);
        return userRepository.findByName(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        log.info("check user exists by user email: " + email);
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public Optional<User> save(User user) {
        log.info("save user: " + user);
        return Optional.of(userRepository.save(user));
    }

}
