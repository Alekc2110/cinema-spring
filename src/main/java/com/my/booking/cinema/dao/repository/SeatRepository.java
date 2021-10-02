package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = "SELECT * FROM `seats` s JOIN`line` l ON s.line_id = l.id JOIN tickets t ON s.id = t.seat_id WHERE t.movie_session_id = ?1", nativeQuery = true)
    List<Seat> getAllBookedSeats(Long msId);

}