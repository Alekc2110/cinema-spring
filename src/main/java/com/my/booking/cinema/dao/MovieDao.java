package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;

import java.util.List;
import java.util.Optional;

public interface MovieDao {

    List<Movie> getAllMovies();

    Optional<Movie> getMovieById(Long movieId);

    Optional<Movie> saveMovie(Movie movie);

}
