package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.SeatDao;
import com.my.booking.cinema.dao.repository.SeatRepository;
import com.my.booking.cinema.model.Seat;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class SeatDaoImpl implements SeatDao {

    private final SeatRepository seatRepository;

    @Override
    public List<Seat> getAllSeats() {
        log.info(" try to return all seats from db");
        List<Seat> allSeats = seatRepository.findAll();
        log.info("returned all seats from db: " + allSeats);
        return allSeats;
    }

    @Override
    public List<Seat> getAllBookedSeats(Long movieSesId) {
        List<Seat> allBookedSeats = seatRepository.getAllBookedSeats(movieSesId);
        log.info("returned all booked seats from db: " + allBookedSeats);
        return allBookedSeats;
    }

    @Override
    public Optional<Seat> getSeatById(Long seatId) {
        log.info("returned optional seat from db by seatId: " + seatId);
        return seatRepository.findById(seatId);
    }

//    @Override
//    public List<Seat> getBookedSeatsByDate(LocalDate date) {
//        log.info("returned all booked seats from db by date: " + date);
//        return seatRepository.getAllBookedSeatsByDate(date);
//    }
}
