package com.my.booking.cinema.dao.impl;

import com.my.booking.cinema.dao.OrderDao;
import com.my.booking.cinema.dao.repository.OrderRepository;
import com.my.booking.cinema.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> saveOrder(Order order) {
        log.info("save order in orderDao: " + order);
        return Optional.of(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Order order) {
        log.info("delete order in orderDao: " + order);
         orderRepository.delete(order);
    }

}
