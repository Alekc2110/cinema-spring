package com.my.booking.cinema.model.web;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableSessionDTO {
    private Long id;
    private String movieTitle;
    private LocalDate date;
    private LocalTime time;

}
