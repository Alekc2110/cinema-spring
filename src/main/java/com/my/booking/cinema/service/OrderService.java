package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.SeatDao;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.SeatDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class OrderService {

    private final SeatDao seatDao;
    private final ModelMapper mapper;

    public OrderService(SeatDao seatDao, ModelMapper mapper) {
        this.seatDao = seatDao;
        this.mapper = mapper;
    }

    public List<SeatDto> getAllSeats(){
        log.info("find all seats in order service");
        return seatDao.getAllSeats().stream().
                map(seat -> mapper.map(seat, SeatDto.class)).collect(Collectors.toList());
    }

    public List<SeatDto> findAllBookedSeats(Long movieSesId){
        log.info("find all booked seats in order service by movie session id: "+ movieSesId);
        return seatDao.getAllBookedSeats(movieSesId).stream().
                map(seat -> mapper.map(seat, SeatDto.class)).collect(Collectors.toList());
    }





}
