package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
