package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.MovieSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    List<MovieSession> getMovieSessionsByMovie(Long movieId);

    MovieSession getMovieSessionById (Long msId);

    List<MovieSession> findMovieSesByDate(LocalDate date);

    Optional<MovieSession> saveMovieSession(MovieSession movieSession);

    void deleteMovieSession(Long movieSesId);
}
