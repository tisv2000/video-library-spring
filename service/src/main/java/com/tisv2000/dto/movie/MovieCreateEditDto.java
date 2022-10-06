package com.tisv2000.dto.movie;

import com.tisv2000.database.entity.Genre;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@FieldNameConstants
public class MovieCreateEditDto {

    @NotBlank(message = "Title must not be empty")
    String newTitle;

    @NotNull(message = "Year must not be empty")
    Integer newYear;

    @NotBlank(message = "Description must not be empty")
    String newDescription;

    @NotBlank(message = "Country must not be empty")
    String newCountry;

    @NotNull(message = "Genre must not be empty")
    Genre newGenre;

    @NotNull(message = "Image must not be empty")
    MultipartFile newImage;
}
