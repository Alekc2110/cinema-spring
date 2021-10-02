package com.my.booking.cinema.controller;

import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import com.my.booking.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Controller
public class MovieController {

    private final MovieService movieService;

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
        List<MovieSessionDto> movieSesByMovieId = movieService.findMovieSesByMovieId(movieById);
        log.info("return manageOrder page in movieController by movie id: " + movieId);
        model.addAttribute("activeMovie", movieById);
        model.addAttribute("movieSessions", movieSesByMovieId);
        return "common/manageOrder";
    }



}
