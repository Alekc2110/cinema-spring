package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Seat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SeatDao {
    List<Seat> getAllSeats();

    List<Seat> getAllBookedSeats(Long movieSesId);

    Optional<Seat> getSeatById(Long seatId);

//    List<Seat> getBookedSeatsByDate(LocalDate date);
}
