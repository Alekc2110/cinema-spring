package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Seat;

import java.util.List;

public interface SeatDao {
    List<Seat> getAllSeats();

    List<Seat> getAllBookedSeats(Long movieSesId);
}
