package com.my.booking.cinema.model.dto;

import com.my.booking.cinema.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDateTime orderTime;
    private UserDto user;
    private int totalPrice;
    private Status orderStatus;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<TicketDto> ticketList;
}
