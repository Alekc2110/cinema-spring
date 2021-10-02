package com.my.booking.cinema.model.dto;

import lombok.*;

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MovieDto movie;

}
