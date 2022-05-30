package com.tisv2000.dto;

import com.tisv2000.database.entity.Genre;
import com.tisv2000.database.entity.Review;
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
    List<Review> reviews;
}
