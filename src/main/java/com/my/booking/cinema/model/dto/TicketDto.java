package com.my.booking.cinema.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    private MovieSessionDto movieSession;
    private SeatDto seat;
    private OrderDto order;
}
