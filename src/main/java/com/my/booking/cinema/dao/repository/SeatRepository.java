package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Seat;
import com.my.booking.cinema.model.dto.SeatDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = "SELECT * FROM `seats` s JOIN `line` l ON s.line_id = l.id JOIN tickets t ON s.id = t.seat_id WHERE t.movie_session_id = ?1", nativeQuery = true)
    List<Seat> getAllBookedSeats(Long msId);

//    @Query(value = "SELECT s FROM Seat s JOIN Line l ON s.line.id = l.id JOIN Ticket t ON s.id = t.seat.id JOIN MovieSession ms ON t.movieSession.id = ms.id WHERE ms.showDate = :date")
//    List<Seat> getAllBookedSeatsByDate(@Param("date") LocalDate date);

}