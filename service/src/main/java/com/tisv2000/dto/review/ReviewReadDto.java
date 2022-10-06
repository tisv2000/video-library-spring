package com.tisv2000.dto.review;

import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.dto.user.UserReadDto;
import lombok.Value;

@Value
public class ReviewReadDto {
    Integer id;
    UserReadDto user;
    MovieReadDto movie;
    String text;
    Integer rate;
}
