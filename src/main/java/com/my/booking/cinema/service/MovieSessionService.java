package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class MovieSessionService {

    private final MovieSessionDao movieSessionDao;
    private final ModelMapper mapper;

    public List<MovieSessionDto> findMovieSesByMovieId(MovieDto movieDto){
        log.info("find movie sessions by movieDto: " + movieDto);
        List<MovieSession> movieSessionsByMovie = movieSessionDao.getMovieSessionsByMovie(mapper.map(movieDto, Movie.class));
        return movieSessionsByMovie.stream().map(ms-> mapper.map(ms, MovieSessionDto.class)).collect(Collectors.toList());
    }

    public MovieSessionDto findMovieSessionById(Long movieSesId){
        log.info("find movie sessions by movie session id: " + movieSesId);
        MovieSession movieSessionsById = movieSessionDao.getMovieSessionById(movieSesId);
        return mapper.map(movieSessionsById, MovieSessionDto.class);
    }
}
