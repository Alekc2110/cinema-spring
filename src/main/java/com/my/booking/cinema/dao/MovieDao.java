package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface MovieDao {

    List<Movie> getAllMovies();

    Page<Movie> getAllMovies(Pageable pageable);

    Optional<Movie> getMovieById(Long movieId);

    Optional<Movie> saveMovie(Movie movie);

    void deleteMovie(Long movieId);



}
