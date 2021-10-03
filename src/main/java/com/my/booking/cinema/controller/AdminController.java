package com.my.booking.cinema.controller;

import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import com.my.booking.cinema.model.web.MovieCreateDto;
import com.my.booking.cinema.model.web.MovieSesCreateDto;
import com.my.booking.cinema.model.web.MovieSesUpdate;
import com.my.booking.cinema.service.MovieService;
import com.my.booking.cinema.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {
    private static final String UPDATE_SUCCESS = "successUpdate";
    private static final String UPDATE_MS_SUCCESS = "successUpdateMS";
    private static final String ADD_SUCCESS_MS = "successAddMS";
    private static final String MOVIE_EDIT = "movieEdit";
    private static final String MOVIE_SES_EDIT = "movieSesEdit";
    private static final String DELETE_SUCCESS = "successDel";
    private static final String DELETE_MS_SUCCESS = "successDelMS";
    private static final int PERCENTAGE_100 = 100;
    private static final int HALL_CAPACITY = 50;
    private static final String PERCENTAGE_BOOKED_SEATS = "percentage";
    private static final String MOVIE_S_LIST_ATTRIBUTE = "moviesSesList";
    private static final String MOVIE_ID = "movieId";
    private static final String MOVIE_TITLE = "movieTitle";

    private final MovieService movieService;
    private final OrderService orderService;

    @GetMapping("/manageMovie")
    public String manageMovies(Model model) {
        List<MovieDto> allMoviesDto = movieService.findAllMovies();
        log.info("return showMovies page in adminController with all movies list: " + allMoviesDto);
        model.addAttribute("movies", allMoviesDto);
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
    public String manageMovies(MovieDto movieEdit, Model model, @PathVariable Long movieId) {
        try {
            log.info("update movie by id: " + movieId);
            movieEdit.setId(movieId);
            movieService.updateMovie(movieEdit);
            model.addAttribute(UPDATE_SUCCESS, "true");
        } catch (EntitySaveDaoException e) {
            model.addAttribute(UPDATE_SUCCESS, "false");
        }
        return "redirect:/admin/manageMovie";
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
    public String manageMovieSession(@PathVariable Long movieId, Model model) {
        List<MovieSessionDto> movieSesList = movieService.findMovieSesByMovieId(movieId);
        model.addAttribute(MOVIE_S_LIST_ATTRIBUTE, movieSesList);
        model.addAttribute(MOVIE_ID, movieId);
        return "admin/movieSession";
    }

    @GetMapping("/movieSession/new/{movieId}")
    public String addMovieSession(@ModelAttribute("movieSession") MovieSesCreateDto movieSession, @PathVariable Long movieId, Model model) {
        model.addAttribute(MOVIE_ID, movieId);
       return "admin/addMovieSession";
    }

    @PostMapping("/movieSession/new/{movieId}")
    public String addMovieSession(@ModelAttribute("movieSession") @Valid MovieSesCreateDto movieSession, BindingResult bindingResult, @PathVariable Long movieId, Model model) {
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
    public String editMovieSession(MovieSesUpdate movieSesEdit, Model model, @PathVariable Long movieSesId) {
        try {
            log.info("update movie session by id: " + movieSesId);
            movieSesEdit.setId(movieSesId);
            movieService.updateMovieSession(movieSesEdit);
            model.addAttribute(UPDATE_MS_SUCCESS, "true");
        } catch (EntitySaveDaoException e) {
            model.addAttribute(UPDATE_MS_SUCCESS, "false");
        }
        return "redirect:/admin/manageMovie";
    }

    @GetMapping("/{movieSesId}/deleteMovieSession")
    public ModelAndView deleteMovieSession(@PathVariable Long movieSesId) {
        log.info("delete movie session with id: " + movieSesId);
        movieService.deleteMovieSession(movieSesId);
        ModelAndView mav = new ModelAndView("redirect:/admin/manageMovie");
        mav.addObject(DELETE_MS_SUCCESS, "true");
        return mav;
    }


    @PostMapping("/showStat")
    public String showAttendanceStat(HttpServletRequest request, Model model) {
        String date = request.getParameter("date");
        if (date != null && !date.isEmpty()) {
            LocalDate showDate = Date.valueOf(date).toLocalDate();
            log.info("show attendance statistics of movie theater by date: " + showDate);
            final int countBookedSeat = orderService.getCountBookedSeatByDate(showDate);
            final float percentage = countBookedSeat / (float) HALL_CAPACITY * PERCENTAGE_100;
            log.info("calculate percentage of attendance: " + percentage);
            model.addAttribute(PERCENTAGE_BOOKED_SEATS, String.format("%.0f", percentage));
            return "redirect:/profile";
        }
        log.info("redirect from Statistics!!!");
        return "redirect:/profile";
    }

}
