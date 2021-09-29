package com.my.booking.cinema.model.dto;

import com.my.booking.cinema.model.Line;
import com.my.booking.cinema.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private Long id;
    private int number;
    private Line line;
    private Status status;


}
