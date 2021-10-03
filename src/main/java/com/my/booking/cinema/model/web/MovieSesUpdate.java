package com.my.booking.cinema.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieSesUpdate {
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate showDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime showTime;
    private int ticketPrice;
}
