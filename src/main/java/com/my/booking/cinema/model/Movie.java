package com.my.booking.cinema.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "movies")
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long Id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "year")
    private String year;

    @Column(name = "country")
    private String country;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<MovieSession> movieSessionList;
}
