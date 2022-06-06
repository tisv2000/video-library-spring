package com.tisv2000.dto.movie;

import com.tisv2000.database.entity.Genre;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieFilterDto {
    String title;
    Integer year;
    String country;
    Genre genre;
}
