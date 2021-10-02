package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.MovieDao;
import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import com.my.booking.cinema.model.web.MovieCreateDto;
import com.my.booking.cinema.model.web.TableSessionDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieService {

    private final MovieDao movieDao;
    private final MovieSessionDao movieSessionDao;
    private final ModelMapper mapper;

    public MovieService(MovieDao movieDao, MovieSessionDao movieSessionDao, ModelMapper mapper) {
        this.movieDao = movieDao;
        this.movieSessionDao = movieSessionDao;
        this.mapper = mapper;
    }

    public List<MovieDto> findAllActiveMovies(){
        log.info("return list of active movies in movieService");
        List<Movie> allMovies = movieDao.getAllMovies();
        return allMovies.stream().
                filter(movie -> movie.getMovieSessionList().size()>0).
                map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());
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

    public void updateMovie(MovieDto movieDto){
        log.info("update movie: " + movieDto);
        Optional<Movie> movieOptional = movieDao.saveMovie(mapper.map(movieDto, Movie.class));
        Movie updated = movieOptional.orElseThrow(() -> new EntitySaveDaoException("could not update movie" + movieDto));
    }

    public void deleteMovie(Long movieId){
        log.info("delete movie by id: " + movieId);
         movieDao.deleteMovie(movieId);
    }

    public List<TableSessionDTO> returnViewSessionList(){
        List<TableSessionDTO> viewList = new ArrayList<>();
        List<Movie> allActiveMovies = movieDao.getAllMovies().stream().
                filter(movie -> movie.getMovieSessionList().size() > 0).collect(Collectors.toList());
        allActiveMovies.forEach(movie ->
                movie.getMovieSessionList().forEach(ms -> {
                    viewList.add(TableSessionDTO.builder().id(movie.getId()).
                            movieTitle(movie.getTitle()).date(ms.getShowDate()).time(ms.getShowTime()).build());
                }));
        return viewList;
    }
}
