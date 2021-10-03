package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.dao.repository.MovieSessionRepo;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
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
        return movieSessionRepo.getAllByMovieId(movieId);
    }

    @Override
    public MovieSession getMovieSessionById(Long msId) {
        return movieSessionRepo.findById(msId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<MovieSession> findMovieSesByDate(LocalDate date) {
        return movieSessionRepo.getAllByShowDate(date);
    }

    @Override
    public Optional<MovieSession> saveMovieSession(MovieSession movieSession) {
        return Optional.of(movieSessionRepo.save(movieSession));
    }

    @Override
    public void deleteMovieSession(Long movieSesId) {
        movieSessionRepo.deleteById(movieSesId);
    }
}
