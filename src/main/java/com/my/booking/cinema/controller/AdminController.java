package com.my.booking.cinema.controller;

import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.web.MovieCreateDto;
import com.my.booking.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {
    private static final String UPDATE_SUCCESS = "successUpdate";
    private static final String MOVIE_EDIT = "movieEdit";
    private static final String DELETE_SUCCESS = "successDel";

    private final MovieService movieService;

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

}
