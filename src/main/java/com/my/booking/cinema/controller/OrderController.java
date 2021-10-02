package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.dto.*;
import com.my.booking.cinema.model.enums.Status;
import com.my.booking.cinema.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@Controller
public class OrderController {
    private static final String ORDER_SESSION_ATTR = "order";
    private static final String MOVIE_ATTR = "movie";
    private static final String MOVIE_SESSION_ATTR = "movieSession";
    private static final String SEATS_LIST_ATTR = "allSeats";
    private static final String ALREADY_BOOKED_SEAT = "bookedSeats";
    private static final String SAVE_ORDER_FALSE = "orderFalse";

    private final OrderService orderService;
    private final MovieService movieService;
    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping("/orderTickets/{movieSesId}")
    public String movieOrder(@PathVariable Long movieSesId, Model model) {
        final MovieSessionDto movieSessionDto = movieService.findMovieSessionById(movieSesId);
        final MovieDto movieById = movieService.findMovieById(movieSessionDto.getMovie().getId());
        List<SeatDto> allSeats = orderService.getAllSeats();
        log.info("find all seats in orderController: " + allSeats.size());
        List<SeatDto> allBookedSeats = orderService.findAllBookedSeats(movieSesId);
        log.info("find all booked seats in orderController: " + allBookedSeats.size());
        allSeats.forEach(seatDto -> seatDto.setStatus(Status.FREE));
        allSeats.stream().filter(allBookedSeats::contains).forEach(seatDto -> seatDto.setStatus(Status.BOOKED));
        log.info("list all seats with status FREE or BOOKED: " + allSeats);
        log.info("return orderTickets page by movieSession id: " + movieSesId);
        model.addAttribute(SEATS_LIST_ATTR, allSeats);
        model.addAttribute(MOVIE_ATTR, movieById);
        model.addAttribute(MOVIE_SESSION_ATTR, movieSessionDto);
        return "common/orderTickets";
    }

    @PostMapping("/addOrder/{movieSesId}")
    public String addOrder(@PathVariable Long movieSesId, HttpServletRequest request, Principal principal, Model model) {
        log.info("create order and return order confirm page");
        List<SeatDto> orderedSeats = new ArrayList<>();
        List<TicketDto> tickets = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        MovieSessionDto movieSessionById = movieService.findMovieSessionById(movieSesId);
        MovieDto movie = movieService.findMovieById(movieSessionById.getMovie().getId());
        log.info("get MovieSession from DB: " + movieSessionById);
        log.info("get movie from DB: " + movie);
        String userName = principal.getName();
        final UserDto userByName = userService.findUserByName(userName);
        String[] seatsIds = parameterMap.get("seatId");
        log.info("get movie from DB: " + movie);
        if (seatsIds.length > 0) {
            for (String seatId : seatsIds) {
                SeatDto seatById = orderService.getSeatById(Long.parseLong(seatId));
                orderedSeats.add(seatById);
            }
            log.info("ordered seats list: " + orderedSeats);
            OrderDto orderDto = OrderDto.builder().
                    orderTime(LocalDateTime.now()).
                    user(userByName).
                    totalPrice(orderedSeats.size() * movieSessionById.getTicketPrice()).
                    orderStatus(Status.BOOKED).
                    build();
            Long savedOrderId = orderService.saveOrder(orderDto);
            orderDto.setId(savedOrderId);
            orderedSeats.forEach(s -> {
                TicketDto ticket = TicketDto.builder().
                        movieSession(movieSessionById).
                        order(orderDto).
                        seat(s).
                        build();
                tickets.add(ticket);
            });
            log.info("tickets list: " + tickets);
            orderDto.setTicketList(new ArrayList<>(tickets));
            log.info("added ticketList to orderDto: " + tickets);
            log.info("create new orderDto and set it to session: " + orderDto);
            HttpSession session = request.getSession();
            model.addAttribute(MOVIE_SESSION_ATTR, movieSessionById);
            model.addAttribute(MOVIE_ATTR, movie);
            session.setAttribute(ORDER_SESSION_ATTR, orderDto);
        }
        return "user/confirmOrder";
    }

    @GetMapping("/confirmOrder/{movieSesId}")
    public String confirmOrder(@PathVariable Long movieSesId, HttpServletRequest request, Model model) {
        log.info("try to confirm order and save tickets");
        List<TicketDto> ticketsBySession = ticketService.findTicketsBySession(movieSesId);
        log.info("tickets list by session: " + ticketsBySession);
        HttpSession session = request.getSession();
        OrderDto order = (OrderDto) session.getAttribute("order");
        log.info("order from session: " + order);
        if (order != null) {
            if (ticketsBySession.size() > 0) {
               if (checkTicketsToBook(ticketsBySession, order)){
                    MovieSessionDto movieSessionById = movieService.findMovieSessionById(movieSesId);
                    orderService.deleteOrder(order);
                    session.removeAttribute("order");
                    model.addAttribute(ALREADY_BOOKED_SEAT, "true");
                    return "forward:/manageMovieOrder/"+ movieSessionById.getMovie().getId();
                }
            }
            order.setOrderStatus(Status.CONFIRMED);
            if (orderService.updateOrder(order)) {
                log.info("order status updated, try to save tickets list");
                if (ticketService.saveTickets(order.getTicketList()))
                    return "redirect:/home";
            }
        }
        MovieSessionDto movieSessionById = movieService.findMovieSessionById(movieSesId);
        orderService.deleteOrder(order);
        session.removeAttribute("order");
        model.addAttribute(SAVE_ORDER_FALSE, "true");
        return "forward:/manageMovieOrder/"+ movieSessionById.getMovie().getId();
    }

    @GetMapping("/cancelOrder")
    public String cancelOrder(HttpServletRequest request) {
        log.info("try to cancel order and delete order from db and session");
        HttpSession session = request.getSession();
        OrderDto order = (OrderDto) session.getAttribute("order");
        session.removeAttribute("order");
        order.setTicketList(Collections.emptyList());
        orderService.deleteOrder(order);
        return "redirect:/home";
    }

    private boolean checkTicketsToBook(List<TicketDto> tickets, OrderDto order) {
        return order.getTicketList().stream().anyMatch(tickets::contains);

    }
}
