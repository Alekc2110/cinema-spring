package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.MovieSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    List<MovieSession> getMovieSessionsByMovie(Long movieId);

    Page<MovieSession> getMovieSessionsByMovie(Long movieId, Pageable pageable);

    Page<MovieSession> getAllMovieSessions(Pageable pageable);

    MovieSession getMovieSessionById (Long msId);

    List<MovieSession> findMovieSesByDate(LocalDate date);

    Optional<MovieSession> saveMovieSession(MovieSession movieSession);

    void deleteMovieSession(Long movieSesId);
}
