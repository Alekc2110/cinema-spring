package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.dao.repository.MovieSessionRepo;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class MovieSessionDaoImpl implements MovieSessionDao {

    private final MovieSessionRepo movieSessionRepo;

    public MovieSessionDaoImpl(MovieSessionRepo movieSessionRepo) {
        this.movieSessionRepo = movieSessionRepo;
    }

    @Override
    public List<MovieSession> getMovieSessionsByMovie(Movie movie) {
        return movieSessionRepo.getAllByMovie(movie);
    }

    @Override
    public MovieSession getMovieSessionById(Long msId) {
        return movieSessionRepo.findById(msId).orElseThrow(EntityNotFoundException::new);
    }
}
