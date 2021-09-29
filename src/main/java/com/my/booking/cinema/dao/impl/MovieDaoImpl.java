package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.MovieDao;
import com.my.booking.cinema.dao.repository.MovieRepository;
import com.my.booking.cinema.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class MovieDaoImpl implements MovieDao {

    private final MovieRepository movieRepository;

    public MovieDaoImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Optional<Movie> saveMovie(Movie movie) {
        return Optional.of(movieRepository.save(movie));
    }

}
