package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieSessionRepo extends JpaRepository<MovieSession, Long> {

    List<MovieSession> getAllByMovie(Movie movie);
}
