package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;

import java.util.List;

public interface MovieSessionDao {
    List<MovieSession> getMovieSessionsByMovie(Movie movie);

    MovieSession getMovieSessionById (Long msId);
}
