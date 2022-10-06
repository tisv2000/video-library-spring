package com.tisv2000.dto.movie;

import com.tisv2000.database.entity.Genre;
import com.tisv2000.database.entity.Role;
import com.tisv2000.dto.review.ReviewReadDto;

import java.util.List;

public class MovieRolesReadDto {
    Integer id;
    String title;
    Integer year;
    String description;
    String country;
    Genre genre;
    String image;
    Role role;
    List<ReviewReadDto> reviews;
}
