package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.dao.repository.MovieSessionRepo;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
public class MovieSessionDaoImpl implements MovieSessionDao {

    private final MovieSessionRepo movieSessionRepo;

    @Override
    public List<MovieSession> getMovieSessionsByMovie(Movie movie) {
        return movieSessionRepo.getAllByMovie(movie);
    }

    @Override
    public MovieSession getMovieSessionById(Long msId) {
        return movieSessionRepo.findById(msId).orElseThrow(EntityNotFoundException::new);
    }
}
