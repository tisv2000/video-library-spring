package com.tisv2000.dto.movie;

import com.tisv2000.database.entity.Genre;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class MovieCreateEditDto {

    String title;
    Integer year;
    String description;
    String country;
    Genre genre;
    String image;

}
