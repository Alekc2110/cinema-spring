package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Ticket;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TicketDao {
    List<Ticket> findTicketsBySession(Long movieSesId);

    List<Ticket> findTicketByUserId(Long userId);

    Page<Ticket> findTicketByUserId(Long userId, Pageable pageable);
}
