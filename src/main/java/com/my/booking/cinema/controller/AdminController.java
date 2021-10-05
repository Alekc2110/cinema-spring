package com.my.booking.cinema.controller;

import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import com.my.booking.cinema.model.web.MovieCreateDto;
import com.my.booking.cinema.model.web.MovieSesCreateDto;
import com.my.booking.cinema.model.web.MovieSesUpdate;
import com.my.booking.cinema.service.MovieService;
import com.my.booking.cinema.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.my.booking.cinema.controller.Constants.*;

@AllArgsConstructor
@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final MovieService movieService;
    private final OrderService orderService;

    @GetMapping("/manageMovie")
    public String manageMovies(Model model, @RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                               @RequestParam(defaultValue = "3", name = "size") Integer pageSize) {
        final Page<Movie> allMoviesPage = movieService.findAllMovies(pageNo, pageSize);
        log.info("return showMovies page in adminController with all movies list: " + allMoviesPage);
        model.addAttribute(DATA, allMoviesPage.getContent());
        model.addAttribute(RECORD_PER_PAGE_AT, allMoviesPage.getSize());
        model.addAttribute(TOTAL_ELEMENTS, allMoviesPage.getTotalElements());
        model.addAttribute(PAGE_NUMBER_AT, allMoviesPage.getNumber());
        model.addAttribute(PAGE_NUMBERS_ATTRIBUTE, allMoviesPage.getTotalPages());
        return "admin/showMovies";
    }

    @GetMapping("/movie/new")
    public String addNewMovie(@ModelAttribute("movie") MovieCreateDto movie) {
        return "admin/addMovie";
    }

    @PostMapping("/movie/new")
    public String addNewMovie(@ModelAttribute("movie") @Valid MovieCreateDto movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addMovie";
        }
        try {
            log.info("try to save new movie in movieController");
            final Long savedMovieId = movieService.saveMovie(movie);
            log.info("saved new movie with id: " + savedMovieId);
            model.addAttribute("success-adding", "true");
        } catch (EntitySaveDaoException e) {
            model.addAttribute("success-adding", "false");
        }
        return "redirect:/admin/manageMovie";
    }

    @GetMapping("/{movieId}/edit")
    public String manageMovies(@PathVariable Long movieId, Model model) {
        final MovieDto movieById = movieService.findMovieById(movieId);
        log.info("return editMovie page with movie: " + movieById);
        model.addAttribute(MOVIE_EDIT, movieById);
        return "admin/editMovie";
    }

    @PostMapping("/{movieId}/edit")
    public ModelAndView manageMovies(@ModelAttribute @Valid MovieDto movieEdit, BindingResult result, @PathVariable Long movieId) {
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()){
            mav.setViewName("redirect:/admin/" + movieId + "/edit");
            return mav;
        }
        try {
            log.info("update movie by id: " + movieId);
            movieEdit.setId(movieId);
            movieService.updateMovie(movieEdit);
            mav.setViewName("redirect:/admin/manageMovie");
            mav.addObject(UPDATE_SUCCESS, "true");
        } catch (EntitySaveDaoException e) {
            mav.addObject(UPDATE_SUCCESS, "false");
        }
        return mav;
    }

    @GetMapping("/{movieId}/delete")
    public ModelAndView deleteMovie(@PathVariable Long movieId) {
        log.info("delete movie with id: " + movieId);
        movieService.deleteMovie(movieId);
        ModelAndView mav = new ModelAndView("redirect:/admin/manageMovie");
        mav.addObject(DELETE_SUCCESS, "true");
        return mav;
    }

    @GetMapping("/manageMovieSession/{movieId}")
    public String manageMovieSession(@PathVariable Long movieId, @RequestParam(defaultValue = "0", name = "page") Integer pageNo,
                                     @RequestParam(defaultValue = "5", name = "size") Integer pageSize, Model model) {
        Page<MovieSession> movieSesList = movieService.findMovieSesByMovieId(movieId, pageNo, pageSize);
        model.addAttribute(MOVIE_ID, movieId);
        model.addAttribute(DATA, movieSesList.getContent());
        model.addAttribute(RECORD_PER_PAGE_AT, movieSesList.getSize());
        model.addAttribute(TOTAL_ELEMENTS, movieSesList.getTotalElements());
        model.addAttribute(PAGE_NUMBER_AT, movieSesList.getNumber());
        model.addAttribute(PAGE_NUMBERS_ATTRIBUTE, movieSesList.getTotalPages());
        return "admin/movieSession";
    }

    @GetMapping("/movieSession/new/{movieId}")
    public String addMovieSession(@ModelAttribute("movieSession") MovieSesCreateDto movieSession,
                                  @PathVariable Long movieId, Model model) {
        model.addAttribute(MOVIE_ID, movieId);
        return "admin/addMovieSession";
    }

    @PostMapping("/movieSession/new/{movieId}")
    public String addMovieSession(@ModelAttribute("movieSession") @Valid MovieSesCreateDto movieSession,
                                  BindingResult bindingResult, @PathVariable Long movieId, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/addMovieSession";
        }
        try {
            log.info("try to save new movie session in movieController");
            final Long savedMovieSesId = movieService.saveMovieSession(movieSession, movieId);
            log.info("saved new movie session with id: " + savedMovieSesId);
            model.addAttribute(ADD_SUCCESS_MS, "true");
        } catch (EntitySaveDaoException e) {
            model.addAttribute(ADD_SUCCESS_MS, "false");
        }
        return "redirect:/admin/manageMovie";
    }

    @GetMapping("/{movieSesId}/editMovieSession")
    public String editMovieSession(@PathVariable Long movieSesId, Model model) {
        final MovieSessionDto movieSessionById = movieService.findMovieSessionById(movieSesId);
        log.info("return editMovieSession page with movie session: " + movieSesId);
        model.addAttribute(MOVIE_TITLE, movieSessionById.getMovie().getTitle());
        model.addAttribute(MOVIE_SES_EDIT, movieSessionById);
        return "admin/editMovieSes";
    }

    @PostMapping("/{movieSesId}/editMovieSession")
    public ModelAndView editMovieSession(@ModelAttribute @Valid MovieSesUpdate movieSesEdit, BindingResult result, @PathVariable Long movieSesId) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
           mav.setViewName("redirect:/admin/" + movieSesId + "/editMovieSession");
           return mav;
        }
        try {
            log.info("update movie session by id: " + movieSesId);
            movieSesEdit.setId(movieSesId);
            movieService.updateMovieSession(movieSesEdit);
            mav.setViewName("redirect:/admin/manageMovie");
            mav.addObject(UPDATE_MS_SUCCESS, "true");
        } catch (EntitySaveDaoException e) {
            mav.addObject(UPDATE_MS_SUCCESS, "false");
        }
        return mav;
    }

    @GetMapping("/{movieSesId}/deleteMovieSession")
    public ModelAndView deleteMovieSession(@PathVariable Long movieSesId) {
        log.info("delete movie session with id: " + movieSesId);
        movieService.deleteMovieSession(movieSesId);
        ModelAndView mav = new ModelAndView("redirect:/admin/manageMovie");
        mav.addObject(DELETE_MS_SUCCESS, "true");
        return mav;
    }


    @GetMapping("/showStat")
    public ModelAndView showAttendanceStat(HttpServletRequest request) {
        String date = request.getParameter("showDate");
        if (date != null && !date.isEmpty()) {
            LocalDate showDate = Date.valueOf(date).toLocalDate();
            log.info("show attendance statistics of movie theater by date: " + showDate);
            final int countBookedSeat = orderService.getCountBookedSeatByDate(showDate);
            final float percentage = countBookedSeat / (float) HALL_CAPACITY * PERCENTAGE_100;
            log.info("calculate percentage of attendance: " + percentage);
            ModelAndView mav = new ModelAndView("redirect:/user/show/profile");
            mav.addObject(PERCENTAGE_BOOKED_SEATS, String.format("%.0f", percentage));
            return mav;
        }
        log.info("redirect from statistics");
        return new ModelAndView("redirect:/user/show/profile");
    }

}
