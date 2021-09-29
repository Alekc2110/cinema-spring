package com.my.booking.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "line")
@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "line_number")
    private int number;

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<Seat> seatList;

    @OneToOne(mappedBy = "line", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Ticket ticket;

}
