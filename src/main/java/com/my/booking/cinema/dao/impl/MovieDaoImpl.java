package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.MovieDao;
import com.my.booking.cinema.dao.repository.MovieRepository;
import com.my.booking.cinema.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class MovieDaoImpl implements MovieDao {

    private final MovieRepository movieRepository;

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

    @Override
    public void deleteMovie(Long movieId) {
        movieRepository.deleteById(movieId);
    }

}
