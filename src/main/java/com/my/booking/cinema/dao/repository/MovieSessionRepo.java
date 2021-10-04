package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.MovieSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface MovieSessionRepo extends JpaRepository<MovieSession, Long> {

    List<MovieSession> getAllByMovieId(Long movieId);

    Page<MovieSession> getAllByMovieId(Long movie_id, Pageable pageable);

    List<MovieSession> getAllByShowDate(LocalDate date);

}
