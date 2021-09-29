package com.my.booking.cinema.model.dto;

import com.my.booking.cinema.model.MovieSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long Id;
    private String title;
    private String description;
    private String director;
    private String year;
    private String country;
    private String photoUrl;
    private List<MovieSession> movieSessionList;
}
