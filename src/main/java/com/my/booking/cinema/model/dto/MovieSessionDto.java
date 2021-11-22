package com.my.booking.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSessionDto {
    private Long id;
    private LocalDate showDate;
    private LocalTime showTime;
    private int ticketPrice;
    private MovieDto movie;

}
