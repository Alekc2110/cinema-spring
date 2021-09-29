package com.my.booking.cinema.controller;

import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import com.my.booking.cinema.model.web.MovieCreateDto;
import com.my.booking.cinema.service.MovieService;
import com.my.booking.cinema.service.MovieSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class MovieController {

    private final MovieService movieService;
    private final MovieSessionService movieSessionService;

    public MovieController(MovieService movieService, MovieSessionService movieSessionService) {
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/nowShowing")
    public String showActiveMovies(Model model) {
        List<MovieDto> allActiveMovies = movieService.findAllActiveMovies();
        log.info("return showActiveMovies page in movieController, all active movies list: " + allActiveMovies);
        model.addAttribute("activeMovies", allActiveMovies);
        return "common/showActiveMovies";
    }

    @GetMapping("/movieDetail/{movieId}")
    public String movieDetail(@PathVariable Long movieId, Model model) {
        MovieDto movieById = movieService.findMovieById(movieId);
        log.info("return movieInfo page in movieController by movie id: " + movieId);
        model.addAttribute("movie", movieById);
        return "common/movieInfo";
    }

    @GetMapping("/manageMovieOrder/{movieId}")
    public String movieOrder(@PathVariable Long movieId, Model model) {
        MovieDto movieById = movieService.findMovieById(movieId);
        List<MovieSessionDto> movieSesByMovieId = movieSessionService.findMovieSesByMovieId(movieById);
        log.info("return manageOrder page in movieController by movie id: " + movieId);
        model.addAttribute("activeMovie", movieById);
        model.addAttribute("movieSessions", movieSesByMovieId);
        return "common/manageOrder";
    }

    @GetMapping("admin/manageMovie")
    public String manageMovies(Model model) {
        List<MovieDto> allMoviesDto = movieService.findAllMovies();
        log.info("return showMovies page in movieController with all movies list: " + allMoviesDto);
        model.addAttribute("movies", allMoviesDto);
        return "admin/showMovies";
    }

    @GetMapping("admin/addMovie")
    public String addNewMovie(@ModelAttribute("movie") MovieCreateDto movie) {
        return "admin/addMovie";
    }

    @PostMapping("admin/addMovie")
    public String addNewPostMovie(@ModelAttribute("movie")@Valid MovieCreateDto movie, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "admin/addMovie";
        }
        try {
            movieService.saveMovie(movie);
            model.addAttribute("success-adding", "true");
        } catch (EntitySaveDaoException e){
            model.addAttribute("success-adding", "false");
        }
        return "admin/showMovies";
    }

}
