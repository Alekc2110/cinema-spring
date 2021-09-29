package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import com.my.booking.cinema.model.dto.SeatDto;
import com.my.booking.cinema.model.enums.Status;
import com.my.booking.cinema.service.MovieService;
import com.my.booking.cinema.service.MovieSessionService;
import com.my.booking.cinema.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MovieService movieService;
    private final MovieSessionService msService;

    public OrderController(OrderService orderService, MovieService movieService, MovieSessionService msService) {
        this.orderService = orderService;
        this.movieService = movieService;
        this.msService = msService;
    }


    @GetMapping("/orderTickets/{movieSesId}")
    public String movieOrder(@PathVariable Long movieSesId, Model model) {
        final MovieSessionDto movieSessionDto = msService.findMovieSessionById(movieSesId);
        final MovieDto movieById = movieService.findMovieById(movieSessionDto.getMovie().getId());
        List<SeatDto> allSeats = orderService.getAllSeats();
        log.info("find all seats in orderController: " + allSeats);
        List<SeatDto> allBookedSeats = orderService.findAllBookedSeats(movieSesId);
        log.info("find all booked seats in orderController: " + allBookedSeats);
        allSeats.forEach(seatDto -> seatDto.setStatus(Status.FREE));
        allSeats.stream().filter(allBookedSeats::contains).forEach(seatDto -> seatDto.setStatus(Status.BOOKED));
        log.info("return orderTickets page by movieSession id: " + movieSesId);
        model.addAttribute("allSeats", allSeats);
        model.addAttribute("movie", movieById);
        model.addAttribute("movieSession", movieSessionDto);
        return "common/orderTickets";
    }


}
