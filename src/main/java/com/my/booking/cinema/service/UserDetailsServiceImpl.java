package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.UserDao;
import com.my.booking.cinema.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user in UserDetailsService with userName: " + username);
        final User user = userDao.findByUserName(username);

        if(user == null){
            throw  new UsernameNotFoundException(String.format("User with name '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthoritiesList(user));
    }

    private List<GrantedAuthority> getAuthoritiesList(User user) {
        return user
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }
}