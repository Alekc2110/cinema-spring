package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.MovieSession;
import com.my.booking.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

    boolean existsUserByEmail (String email);



}
