package com.my.booking.cinema.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreateDto {
    @NotEmpty(message = "min 2 max 20 letters")
    @Size(min = 2, max = 20)
    private String title;
    @NotEmpty(message = "min 2 max 1000 letters")
    @Size(min = 2, max = 1000)
    private String description;
    @NotEmpty(message = "min 2 max 20 letters")
    @Size(min = 2, max = 20)
    private String director;
    @NotEmpty(message = "4 digits")
    @Size(min = 4, max = 4)
    private String year;
    @NotEmpty(message = "min 2 max 10 letters")
    @Size(min = 2, max = 10)
    private String country;
    @NotEmpty(message = "min 2 max 100 symbols")
    @Size(min = 2, max = 100)
    private String photoUrl;
}
