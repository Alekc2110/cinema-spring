package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.dao.repository.MovieSessionRepo;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;


import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class MovieSessionDaoImpl implements MovieSessionDao {

    private final MovieSessionRepo movieSessionRepo;

    @Override
    public List<MovieSession> getMovieSessionsByMovie(Long movieId) {
        final List<MovieSession> allByMovieId = movieSessionRepo.getAllByMovieId(movieId);
        log.info("get movie sessions list by movieId in MovieSessionDao: " + allByMovieId);
        return allByMovieId;
    }

    @Override
    public Page<MovieSession> getMovieSessionsByMovie(Long movieId, Pageable pageable) {
        log.info("get movie sessions page by movieId: " + movieId);
        return movieSessionRepo.getAllByMovieId(movieId, pageable);
    }

    @Override
    public MovieSession getMovieSessionById(Long msId) {
        log.info("get movie session by movieSesId: " + msId);
        return movieSessionRepo.findById(msId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<MovieSession> findMovieSesByDate(LocalDate date) {
        final List<MovieSession> allByShowDate = movieSessionRepo.getAllByShowDate(date);
        log.info("get movie sessions list by date: " + date);
        return allByShowDate;
    }

    @Override
    public Optional<MovieSession> saveMovieSession(MovieSession movieSession) {
        log.info("save movie session: " + movieSession);
        return Optional.of(movieSessionRepo.save(movieSession));
    }

    @Override
    public void deleteMovieSession(Long movieSesId) {
        log.info("delete movie session by id: " + movieSesId);
        movieSessionRepo.deleteById(movieSesId);
    }
}
