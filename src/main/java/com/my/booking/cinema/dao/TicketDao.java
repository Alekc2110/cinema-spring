package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Ticket;

import java.util.List;

public interface TicketDao {
    List<Ticket> findTicketsBySession(Long movieSesId);

    List<Ticket> findTicketByUserId(Long userId);
}
