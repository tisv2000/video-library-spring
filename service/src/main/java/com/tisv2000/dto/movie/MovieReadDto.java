package com.tisv2000.dto.movie;

import com.tisv2000.database.entity.Genre;
import com.tisv2000.dto.review.ReviewReadDto;
import lombok.Value;

import java.util.List;

@Value
public class MovieReadDto {
    Integer id;
    String title;
    Integer year;
    String description;
    String country;
    Genre genre;
    String image;
    List<ReviewReadDto> reviews;
}
