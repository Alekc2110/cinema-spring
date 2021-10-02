package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.TicketDto;
import com.my.booking.cinema.model.dto.UserDto;
import com.my.booking.cinema.model.web.TableSessionDTO;
import com.my.booking.cinema.service.MovieService;
import com.my.booking.cinema.service.TicketService;
import com.my.booking.cinema.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RequestMapping("/user")
@Controller
public class UserController {
    private static final String MOVIE_LIST_ATTRIBUTE = "movieList";
    private static final String TICKET_LIST_ATTRIBUTE = "ticketList";
    private static final String SORT_OPTION = "option";
    private static final String SORT_BY_TITLE = "title";
    private static final String SORT_BY_DATE = "date";
    private static final String SORT_BY_TIME = "time";

    private final MovieService movieService;
    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping("/show/MovieTable")
    public String showMovieTable(Model model) {
        final List<TableSessionDTO> viewList = movieService.returnViewSessionList();
        log.info("return movie table page in userController with all sessions list: " + viewList);
        model.addAttribute(MOVIE_LIST_ATTRIBUTE, viewList);
        return "user/showMoviesTable";
    }

    @PostMapping("/show/MovieTable")
    public String showMovieTable(@ModelAttribute("option") String option, Model model) {
        final List<TableSessionDTO> viewList = movieService.returnViewSessionList();
        if(option != null && !option.isEmpty()){
            if (option.equals(SORT_BY_TITLE)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getMovieTitle));
                model.addAttribute(SORT_OPTION, SORT_BY_TITLE);
            }
            if (option.equals(SORT_BY_DATE)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getDate));
                model.addAttribute(SORT_OPTION, SORT_BY_DATE);
            }
            if (option.equals(SORT_BY_TIME)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getTime));
                model.addAttribute(SORT_OPTION, SORT_BY_TIME);
            }

        }
        log.info("return movie table page in userController with sorted sessions list: " + viewList);
        model.addAttribute(MOVIE_LIST_ATTRIBUTE, viewList);
        return "user/showMoviesTable";
    }

    @GetMapping("/show/ticketsTable")
    public String showTicketsTable(Principal principal, Model model) {
        log.info("show bought tickets list");
        final UserDto userByName = userService.findUserByName(principal.getName());
        log.info("found user: " + userByName);
        final List<TicketDto> ticketsByUserId = ticketService.findTicketByUserId(userByName.getId());
        model.addAttribute(TICKET_LIST_ATTRIBUTE, ticketsByUserId);
        return "user/showTicketsTable";
    }


}
