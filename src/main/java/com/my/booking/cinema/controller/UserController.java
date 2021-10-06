package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.MovieSession;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

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
        Page<MovieSession> pageMovSession;
        if (sortOption != null && !sortOption.isEmpty()) {
            pageMovSession = movieService.findAllMovieSessions(pageNo, pageSize, sortOption);
            model.addAttribute(SORT_OPTION, sortOption);
        } else {
            pageMovSession = movieService.findAllMovieSessions(pageNo, pageSize);
        }
        final int totalPages = pageMovSession.getTotalPages();
        log.info("return total movie sessions pages number: " + totalPages);
        final List<TableSessionDTO> viewList = movieService.returnViewList(pageMovSession.getContent());
        log.info("return movie session table page in userController: " + viewList);
        log.info("return movie session table page *size* in userController: " + viewList.size());
        model.addAttribute(DATA, viewList);
        model.addAttribute(RECORD_PER_PAGE_AT, pageMovSession.getSize());
        model.addAttribute(PAGE_NUMBERS_ATTRIBUTE, pageMovSession.getTotalPages());
        return "user/showMoviesTable";
    }

    @GetMapping("/ticketsTable")
    public String showTicketsTable(Principal principal, @RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                   @RequestParam(defaultValue = "5", name = "size") Integer pageSize, Model model) {
        log.info("show bought tickets list table");
        final UserDto userByName = userService.findUserByName(principal.getName());
        log.info("found user: " + userByName);
        final Page<TicketDto> ticketPage = ticketService.findTicketByUserId(userByName.getId(), pageNo, pageSize);
        model.addAttribute(DATA, ticketPage.getContent());
        model.addAttribute(RECORD_PER_PAGE_AT, ticketPage.getSize());
        model.addAttribute(TOTAL_ELEMENTS, ticketPage.getTotalElements());
        model.addAttribute(PAGE_NUMBER_AT, ticketPage.getNumber());
        model.addAttribute(PAGE_NUMBERS_ATTRIBUTE, ticketPage.getTotalPages());

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
        if (date != null && !date.toString().isEmpty()) {
            final int countBookedSeat = orderService.getCountBookedSeatByDate(date);
            final float percentage = countBookedSeat / (float) HALL_CAPACITY * PERCENTAGE_100;
            log.info("calculate percentage of attendance: " + percentage);
            ModelAndView mav = new ModelAndView("redirect:/user/show/profile");
            mav.addObject(PERCENTAGE_BOOKED_SEATS, String.format("%.0f", percentage));

        }
        return new ModelAndView("user/userAccount");
    }

}
