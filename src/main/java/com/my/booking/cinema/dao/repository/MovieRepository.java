package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
