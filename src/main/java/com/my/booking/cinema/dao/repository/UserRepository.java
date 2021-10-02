package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

    boolean existsUserByEmail (String email);



}
