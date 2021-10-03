package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.dto.TicketDto;
import com.my.booking.cinema.model.dto.UserDto;
import com.my.booking.cinema.model.web.TableSessionDTO;
import com.my.booking.cinema.service.MovieService;
import com.my.booking.cinema.service.TicketService;
import com.my.booking.cinema.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Slf4j
@RequestMapping("/user/show")
@Controller
public class UserController {
    private static final String MOVIE_LIST_ATTRIBUTE = "movieList";
    private static final String TICKET_LIST_ATTRIBUTE = "ticketList";
    private static final String SORT_OPTION = "option";
    private static final String SORT_BY_TITLE = "title";
    private static final String SORT_BY_DATE = "date";
    private static final String SORT_BY_TIME = "time";
    private static final String MOVIE_SES_LIST_TODAY = "movieListNow";
    private static final String MOVIE_SES_LIST_TOM = "movieListTomorrow";
    private static final String MOVIE_SES_LIST_NOW2 = "movieListNowPlTwo";
    private static final String MOVIE_SES_LIST_NOW3 = "movieListNowPlThree";
    private static final String MOVIE_SES_LIST_NOW4 = "movieListNowPlFour";
    private static final String MOVIE_SES_LIST_NOW5 = "movieListNowPlFive";
    private static final String MOVIE_SES_LIST_NOW6 = "movieListNowPlSix";

    private final MovieService movieService;
    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping("/movieTable")
    public String showMovieTable(Model model) {
        final List<TableSessionDTO> viewList = movieService.returnViewSessionList();
        log.info("return movie table page in userController with all sessions list: " + viewList);
        model.addAttribute(MOVIE_LIST_ATTRIBUTE, viewList);
        return "user/showMoviesTable";
    }

    @PostMapping("/movieTable")
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

    @GetMapping("/ticketsTable")
    public String showTicketsTable(Principal principal, Model model) {
        log.info("show bought tickets list table");
        final UserDto userByName = userService.findUserByName(principal.getName());
        log.info("found user: " + userByName);
        final List<TicketDto> ticketsByUserId = ticketService.findTicketByUserId(userByName.getId());
        model.addAttribute(TICKET_LIST_ATTRIBUTE, ticketsByUserId);
        return "user/showTicketsTable";
    }

    @GetMapping("/timeTable")
    public String showMovieTimeTable(Model model) {
        log.info("show movie timetable for week");
        final List<TableSessionDTO> timeTableNow = movieService.getTimeTableByDate(LocalDate.now());
        final List<TableSessionDTO> timeTableTomorrow = movieService.getTimeTableByDate(LocalDate.now().plusDays(1L));
        final List<TableSessionDTO> timeTableNowPlus2 = movieService.getTimeTableByDate(LocalDate.now().plusDays(2L));
        final List<TableSessionDTO> timeTableNowPlus3 = movieService.getTimeTableByDate(LocalDate.now().plusDays(3L));
        final List<TableSessionDTO> timeTableNowPlus4 = movieService.getTimeTableByDate(LocalDate.now().plusDays(4L));
        final List<TableSessionDTO> timeTableNowPlus5 = movieService.getTimeTableByDate(LocalDate.now().plusDays(5L));
        final List<TableSessionDTO> timeTableNowPlus6 = movieService.getTimeTableByDate(LocalDate.now().plusDays(6L));
        model.addAttribute(MOVIE_SES_LIST_TODAY, timeTableNow);
        model.addAttribute(MOVIE_SES_LIST_TOM, timeTableTomorrow);
        model.addAttribute(MOVIE_SES_LIST_NOW2, timeTableNowPlus2);
        model.addAttribute(MOVIE_SES_LIST_NOW3, timeTableNowPlus3);
        model.addAttribute(MOVIE_SES_LIST_NOW4, timeTableNowPlus4);
        model.addAttribute(MOVIE_SES_LIST_NOW5, timeTableNowPlus5);
        model.addAttribute(MOVIE_SES_LIST_NOW6, timeTableNowPlus6);
        return "user/timeTable";
    }

}
