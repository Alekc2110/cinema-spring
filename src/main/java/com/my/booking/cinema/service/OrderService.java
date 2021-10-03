package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.dao.OrderDao;
import com.my.booking.cinema.dao.SeatDao;
import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.MovieSession;
import com.my.booking.cinema.model.Order;
import com.my.booking.cinema.model.Seat;
import com.my.booking.cinema.model.dto.OrderDto;
import com.my.booking.cinema.model.dto.SeatDto;
import com.my.booking.cinema.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderDao orderDao;
    private final SeatDao seatDao;
    private final MovieSessionDao movieSessionDao;
    private final ModelMapper mapper;

    public List<SeatDto> getAllSeats() {
        log.info("find all seats in order service");
        return seatDao.getAllSeats().stream().
                map(seat -> mapper.map(seat, SeatDto.class)).collect(Collectors.toList());
    }

    public List<SeatDto> findAllBookedSeats(Long movieSesId) {
        log.info("find all booked seats in order service by movie session id: " + movieSesId);
        return seatDao.getAllBookedSeats(movieSesId).stream().
                map(seat -> mapper.map(seat, SeatDto.class)).collect(Collectors.toList());
    }

    public SeatDto getSeatById(Long seatId) {
        log.info("try to return seat by seatId: " + seatId);
        Seat seat = seatDao.getSeatById(seatId).orElseThrow(EntityNotFoundException::new);
        log.info("return seat : " + seat);
        return mapper.map(seat, SeatDto.class);
    }

    public Long saveOrder(OrderDto order) {
        log.info("save new order with status BOOKED: " + order);
        final Optional<Order> saved = orderDao.saveOrder(mapper.map(order, Order.class));
        Order savedOrder = saved.orElseThrow(EntitySaveDaoException::new);
        return savedOrder.getId();
    }

    public boolean updateOrder(OrderDto order) {
        log.info("update order: " + order);
        Optional<Order> orderOptional = orderDao.saveOrder(mapper.map(order, Order.class));
        Order updated = orderOptional.orElseThrow(() -> new EntitySaveDaoException("could not update order" + order));
        return updated.getOrderStatus().equals(Status.CONFIRMED);
    }

    public void deleteOrder(OrderDto order) {
        log.info("delete order: " + order);
        orderDao.deleteOrder(mapper.map(order, Order.class));
    }

    public int getCountBookedSeatByDate(LocalDate date) {
        AtomicInteger count = new AtomicInteger();
        log.info("return count of booked seats by date: " + date);
        final List<MovieSession> movieSesByDate = movieSessionDao.findMovieSesByDate(date);
        log.info("movie session list: " + movieSesByDate);
        final List<Seat> bookedSeats = movieSesByDate.stream().map(ms -> seatDao.getAllBookedSeats(ms.getId())).flatMap(List::stream).collect(Collectors.toList());
        log.info("return list booked seats by date: " + bookedSeats);
        bookedSeats.forEach(seat -> count.getAndIncrement());
        return count.get();
    }
}
