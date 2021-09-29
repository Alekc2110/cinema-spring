package com.my.booking.cinema.model;

import com.my.booking.cinema.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ToString.Include
    private Status orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Ticket> ticketList;


}
