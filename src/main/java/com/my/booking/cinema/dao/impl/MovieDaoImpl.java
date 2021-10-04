package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.MovieDao;
import com.my.booking.cinema.dao.repository.MovieRepository;
import com.my.booking.cinema.model.Movie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        log.info("get all movies list");
        return movieRepository.findAll();
    }

    @Override
    public Page<Movie> getAllMovies(Pageable pageable) {
        log.info("get all movies page");
        return movieRepository.findAll(pageable);
    }

    @Override
    public Optional<Movie> getMovieById(Long movieId) {
        log.info("return optional of movie by id: " + movieId);
        return movieRepository.findById(movieId);
    }

    @Override
    public Optional<Movie> saveMovie(Movie movie) {
        log.info("save movie: " + movie);
        return Optional.of(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long movieId) {
        log.info("delete movie by id: " + movieId);
        movieRepository.deleteById(movieId);
    }

}
