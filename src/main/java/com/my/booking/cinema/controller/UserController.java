package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.dto.TicketDto;
import com.my.booking.cinema.model.dto.UserDto;
import com.my.booking.cinema.model.web.TableSessionDTO;
import com.my.booking.cinema.service.MovieService;
import com.my.booking.cinema.service.OrderService;
import com.my.booking.cinema.service.TicketService;
import com.my.booking.cinema.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

import static com.my.booking.cinema.controller.Constants.*;

@AllArgsConstructor
@Slf4j
@RequestMapping("/user/show")
@Controller
public class UserController {

    private final MovieService movieService;
    private final UserService userService;
    private final TicketService ticketService;
    private final OrderService orderService;

    @GetMapping("/movieTable")
    public String showMovieTable(Model model, @RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                 @RequestParam(defaultValue = "5", name = "size") Integer pageSize,
                                 @RequestParam(defaultValue = "", name = "option") String sortOption) {
        final Page<Movie> pageMovies = movieService.findAllMovies(pageNo, pageSize);
        final int totalPages = pageMovies.getTotalPages();
        log.info("return page movies: " + pageMovies);
        log.info("return total pages number: " + totalPages);
        final List<TableSessionDTO> viewList = movieService.returnViewList(pageMovies);
        if (sortOption != null && !sortOption.isEmpty()) {
            if (sortOption.equals(SORT_BY_TITLE)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getMovieTitle));
                model.addAttribute(SORT_OPTION, SORT_BY_TITLE);
            }
            if (sortOption.equals(SORT_BY_DATE)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getDate));
                model.addAttribute(SORT_OPTION, SORT_BY_DATE);
            }
            if (sortOption.equals(SORT_BY_TIME)) {
                viewList.sort(Comparator.comparing(TableSessionDTO::getTime));
                model.addAttribute(SORT_OPTION, SORT_BY_TIME);
            }
        }

        log.info("return movie table page in userController with all sessions list: " + viewList);
        model.addAttribute(DATA, viewList);
        model.addAttribute(RECORD_PER_PAGE_AT, pageMovies.getSize());
        model.addAttribute(TOTAL_ELEMENTS, pageMovies.getTotalElements());
        model.addAttribute(PAGE_NUMBER_AT, pageMovies.getNumber());
        model.addAttribute(PAGE_NUMBERS_ATTRIBUTE, pageMovies.getTotalPages());
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

    @GetMapping("/profile")
    public ModelAndView profilePage(Principal principal, Model model, @RequestParam(defaultValue = "", name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        final UserDto userByName = userService.findUserByName(principal.getName());
        log.info("return userAccount page in MainController by user name: " + userByName);
        model.addAttribute(USER, userByName);
        if(date != null && !date.toString().isEmpty()) {
            final int countBookedSeat = orderService.getCountBookedSeatByDate(date);
            final float percentage = countBookedSeat / (float) HALL_CAPACITY * PERCENTAGE_100;
            log.info("calculate percentage of attendance: " + percentage);
            ModelAndView mav = new ModelAndView("redirect:/user/show/profile");
            mav.addObject(PERCENTAGE_BOOKED_SEATS, String.format("%.0f", percentage));

        }
        return new ModelAndView("user/userAccount");
    }

}
