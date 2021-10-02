package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
