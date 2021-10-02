package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.UserDao;
import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.User;
import com.my.booking.cinema.model.dto.UserDto;
import com.my.booking.cinema.model.enums.Role;
import com.my.booking.cinema.model.web.SignupRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class UserService {

    private UserDao userDao;
    private PasswordEncoder encoder;
    private ModelMapper mapper;

    public UserDto findUserByName(String name) {
        return mapper.map(userDao.findByUserName(name), UserDto.class);
    }

    public boolean saveUser(SignupRequest userCreate) {
        log.info("save new user : " + userCreate);
        if (userDao.existsUserByEmail(userCreate.getEmail())) {
            return false;
        }
        final User user = User.builder().
                email(userCreate.getEmail()).
                name(userCreate.getName()).
                password(encoder.encode(userCreate.getPassword())).
                roles(List.of(Role.USER)).build();

        Optional<User> saved = userDao.save(user);
        final User savedUser = saved.orElseThrow(EntitySaveDaoException::new);
        log.info("saved new user with id: " + savedUser.getId());
        return true;
    }


}
