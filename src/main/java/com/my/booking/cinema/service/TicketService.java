package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.TicketDao;
import com.my.booking.cinema.model.Ticket;
import com.my.booking.cinema.model.dto.TicketDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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

    public Page<TicketDto> findTicketByUserId(Long userId, Integer pageNo, Integer pageSize){
        log.info(String.format("return Page tickets by pageNo: %s and pageSize: %s in ticketService", pageNo, pageSize));
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Ticket> ticketsBySession = ticketDao.findTicketByUserId(userId, paging);
        return ticketsBySession.map(t -> mapper.map(t, TicketDto.class));
    }


}
