package com.my.booking.cinema.dao;

import com.my.booking.cinema.model.Order;

import java.util.Optional;

public interface OrderDao {
    Optional<Order> saveOrder(Order order);

    void deleteOrder(Order order);
}
