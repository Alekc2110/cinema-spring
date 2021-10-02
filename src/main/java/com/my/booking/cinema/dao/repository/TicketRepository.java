package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT * FROM `tickets` t JOIN `movie_sessions` ms ON t.movie_session_id = ms.id " +
            "JOIN `orders` o ON t.order_id = o.id JOIN `seats` s ON t.seat_id = s.id " +
            "WHERE t.movie_session_id = ?1", nativeQuery = true)
    List<Ticket> findAllByMovieSessions(Long movieSesId);

    @Query(value = "SELECT * FROM `tickets` t JOIN `movie_sessions` ms ON t.movie_session_id = ms.id " +
            "JOIN `seats` s ON t.seat_id = s.id JOIN `orders` o ON t.order_id = o.id WHERE o.user_id = ?1", nativeQuery = true)
    List<Ticket> findAllByUserId(Long userId);


}
