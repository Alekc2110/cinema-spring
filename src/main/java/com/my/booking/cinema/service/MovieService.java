package com.my.booking.cinema.service;

import com.my.booking.cinema.dao.MovieDao;
import com.my.booking.cinema.dao.MovieSessionDao;
import com.my.booking.cinema.exception.EntitySaveDaoException;
import com.my.booking.cinema.model.Movie;
import com.my.booking.cinema.model.MovieSession;
import com.my.booking.cinema.model.dto.MovieDto;
import com.my.booking.cinema.model.dto.MovieSessionDto;
import com.my.booking.cinema.model.web.MovieCreateDto;
import com.my.booking.cinema.model.web.MovieSesCreateDto;
import com.my.booking.cinema.model.web.MovieSesUpdate;
import com.my.booking.cinema.model.web.TableSessionDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    public List<MovieDto> findAllActiveMovies() {
        log.info("return list of active movies in movieService");
        List<Movie> allMovies = movieDao.getAllMovies();
        return allMovies.stream().
                filter(movie -> movie.getMovieSessionList().size() > 0).
                map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());
    }

    public MovieDto findMovieById(Long movieId) {
        log.info("find movie by id: " + movieId);
        Movie movie = movieDao.getMovieById(movieId).orElseThrow(EntityNotFoundException::new);
        return mapper.map(movie, MovieDto.class);
    }

    public List<MovieDto> findAllMovies() {
        log.info("return all movies list in movieService");
        return movieDao.getAllMovies().stream().
                map(movie -> mapper.map(movie, MovieDto.class)).collect(Collectors.toList());
    }

    public Long saveMovie(MovieCreateDto movieCreateDto) {
        log.info("save new movie in movieService: " + movieCreateDto);
        return movieDao.saveMovie(mapper.map(movieCreateDto, Movie.class)).
                map(Movie::getId).orElseThrow(EntitySaveDaoException::new);
    }

    public List<MovieSessionDto> findMovieSesByMovieId(Long movieId) {
        log.info("find movie sessions by movieId: " + movieId);
        List<MovieSession> movieSessionsByMovie = movieSessionDao.getMovieSessionsByMovie(movieId);
        return movieSessionsByMovie.stream().map(ms -> mapper.map(ms, MovieSessionDto.class)).collect(Collectors.toList());
    }

    public MovieSessionDto findMovieSessionById(Long movieSesId) {
        log.info("find movie sessions by movie session id: " + movieSesId);
        MovieSession movieSessionsById = movieSessionDao.getMovieSessionById(movieSesId);
        return mapper.map(movieSessionsById, MovieSessionDto.class);
    }

    public void updateMovie(MovieDto movieDto) {
        log.info("update movie: " + movieDto);
        final Optional<Movie> movieOptional = movieDao.saveMovie(mapper.map(movieDto, Movie.class));
        movieOptional.orElseThrow(() -> new EntitySaveDaoException("could not update movie" + movieDto));
    }

    public void deleteMovie(Long movieId) {
        log.info("delete movie by id: " + movieId);
        movieDao.deleteMovie(movieId);
    }

    public Long saveMovieSession(MovieSesCreateDto movieSesCreate, Long movieId) {
        log.info("save new movie session in movieService: " + movieSesCreate);
        final Movie movie = movieDao.getMovieById(movieId).orElseThrow(EntityNotFoundException::new);
        final MovieSession movieSession = mapper.map(movieSesCreate, MovieSession.class);
        movieSession.setMovie(movie);
        return movieSessionDao.saveMovieSession(movieSession).
                map(MovieSession::getId).orElseThrow(EntitySaveDaoException::new);
    }

    public void updateMovieSession(MovieSesUpdate movieSesUpdate) {
        log.info("update movie session: " + movieSesUpdate);
        final MovieSession movieSessionById = movieSessionDao.getMovieSessionById(movieSesUpdate.getId());
        movieSessionById.setShowDate(movieSesUpdate.getShowDate());
        movieSessionById.setShowTime(movieSesUpdate.getShowTime());
        movieSessionById.setTicketPrice(movieSesUpdate.getTicketPrice());
        final Optional<MovieSession> movieSession = movieSessionDao.saveMovieSession(movieSessionById);
        movieSession.orElseThrow(() -> new EntitySaveDaoException("could not update movie session" + movieSesUpdate));
    }


    public List<TableSessionDTO> returnViewSessionList() {
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

    public List<TableSessionDTO> getTimeTableByDate(LocalDate date) {
        List<TableSessionDTO> view = new ArrayList<>();
        final List<MovieSession> sorted = movieSessionDao.findMovieSesByDate(date).
                stream().sorted(Comparator.comparing(MovieSession::getShowTime)).collect(Collectors.toList());
        log.info("return sorted list for timetable by date : " + sorted);
        if (sorted.size() == 0) {
            TableSessionDTO newDto = new TableSessionDTO();
            newDto.setMovieTitle("");
            newDto.setDate(date);
            view.add(newDto);
        }
        sorted.forEach(m -> {
            TableSessionDTO newDto = new TableSessionDTO();
            newDto.setMovieTitle(m.getMovie().getTitle());
            newDto.setDate(m.getShowDate());
            newDto.setTime(m.getShowTime());
            view.add(newDto);
        });
        return view;
    }

    public void deleteMovieSession(Long movieSesId) {
        log.info("delete movie by id: " + movieSesId);
        movieSessionDao.deleteMovieSession(movieSesId);
    }

}
