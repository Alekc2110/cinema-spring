package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.MovieDao;
import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.web.MovieCreateDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieService {

    private final MovieDao movieDao;
    private final ModelMapper mapper;

    public MovieService(MovieDao movieDao, ModelMapper mapper) {
        this.movieDao = movieDao;
        this.mapper = mapper;
    }

    public List<MovieDto> findAllActiveMovies(){
        log.info("return list of active movies in movieService");
        List<MovieDto> allMovieDtos = movieDao.getAllMovies().stream().
                map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());

        return allMovieDtos.stream().
                filter(movie -> movie.getMovieSessionList().size()>0).collect(Collectors.toList());
    }

    public MovieDto findMovieById(Long movieId){
        log.info("find movie by id: " + movieId);
        Movie movie = movieDao.getMovieById(movieId).orElseThrow(EntityNotFoundException::new);
        return mapper.map(movie, MovieDto.class);
    }

    public List<MovieDto> findAllMovies(){
        log.info("return all movies list in movieService");
        return  movieDao.getAllMovies().stream().
                map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());
    }

    public Long saveMovie(MovieCreateDto movieCreateDto){
        log.info("save new movie in movieService: " + movieCreateDto);
        return movieDao.saveMovie(mapper.map(movieCreateDto, Movie.class)).
                map(Movie::getId).orElseThrow(EntitySaveDaoException::new);
    }
}
