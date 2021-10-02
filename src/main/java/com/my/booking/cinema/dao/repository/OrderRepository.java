package com.my.booking.cinema.dao.repository;

import com.my.booking.cinema.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
