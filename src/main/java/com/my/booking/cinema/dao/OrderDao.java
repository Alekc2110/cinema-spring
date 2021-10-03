package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Order;
import com.my.booking.cinema.model.Seat;

import java.time.LocalDate;
import java.util.Optional;

public interface OrderDao {
    Optional<Order> saveOrder(Order order);

    void deleteOrder(Order order);

}
