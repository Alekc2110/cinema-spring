package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.TicketDao;
import com.my.booking.cinema.model.Ticket;
import com.my.booking.cinema.model.dto.TicketDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class TicketService {

    private final TicketDao ticketDao;
    private final ModelMapper mapper;

    public List<TicketDto> findTicketsBySession(Long movieSesId){
        List<Ticket> ticketsBySession = ticketDao.findTicketsBySession(movieSesId);
        return ticketsBySession.stream().map(ticket-> mapper.map(ticket, TicketDto.class)).collect(Collectors.toList());
    }

    @Transactional
    public boolean saveTickets(List<TicketDto> tickets){
        List<Ticket> ticketsList = tickets.stream().map(ticketDto -> mapper.map(ticketDto, Ticket.class)).collect(Collectors.toList());
        List<Ticket> savedList = ticketDao.saveTickets(ticketsList);
        return ticketsList.size() == savedList.size();
    }

}
