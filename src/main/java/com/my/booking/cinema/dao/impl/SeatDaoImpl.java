package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.SeatDao;
import com.my.booking.cinema.dao.repository.SeatRepository;
import com.my.booking.cinema.model.Seat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatDaoImpl implements SeatDao {

    private final SeatRepository seatRepository;

    public SeatDaoImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @Override
    public List<Seat> getAllBookedSeats(Long movieSesId) {
        return seatRepository.getAllBookedSeats(movieSesId);
    }
}
