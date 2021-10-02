package com.my.booking.cinema.model;

import com.my.booking.cinema.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    @ToString.Include
    private Status orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Ticket> ticketList;

}
