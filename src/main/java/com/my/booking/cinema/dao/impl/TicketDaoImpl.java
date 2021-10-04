package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.TicketDao;
import com.my.booking.cinema.dao.repository.TicketRepository;
import com.my.booking.cinema.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Component
public class TicketDaoImpl implements TicketDao {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> findTicketsBySession(Long movieSesId) {
        List<Ticket> allTicketsByMS = ticketRepository.findAllByMovieSessions(movieSesId);
        log.info("returned all tickets by movie session: " + allTicketsByMS);
        return allTicketsByMS;
    }

    @Override
    public List<Ticket> findTicketByUserId(Long userId) {
        log.info("get tickets list by userId: " + userId);
        return ticketRepository.findAllByUserId(userId);
    }
}
