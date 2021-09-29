package com.my.booking.cinema.model.dto;

import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSessionDto {
    private Long id;
    private LocalDate showDate;
    private LocalTime showTime;
    private int ticketPrice;
    private Movie movie;

}
