package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query(value = "SELECT * FROM movie_sessions_booked_seats ms JOIN seat s ON ms.seat_id = s.id WHERE ms.movie_session_id = ?1", nativeQuery = true)
    List<Seat> getAllBookedSeats(Long msId);
}