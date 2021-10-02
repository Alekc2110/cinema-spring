package com.my.booking.cinema.model.dto;

import com.my.booking.cinema.model.enums.Status;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {
    private Long id;
    private int number;
    private LineDto line;
    @EqualsAndHashCode.Exclude
    private Status status;


}
