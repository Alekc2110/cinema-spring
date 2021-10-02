package com.my.booking.cinema.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineDto {
    private Long id;
    private int number;
}
