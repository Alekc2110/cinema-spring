package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

public interface MovieSessionRepo extends JpaRepository<MovieSession, Long> {

    List<MovieSession> getAllByMovieId(Long movieId);

    List<MovieSession> getAllByShowDate(LocalDate date);

}
