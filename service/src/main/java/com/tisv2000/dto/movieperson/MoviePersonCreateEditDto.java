package com.tisv2000.dto.movieperson;

import com.tisv2000.database.entity.PersonRole;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@Builder
public class MoviePersonCreateEditDto {

    @NotNull(message = "Movie must not be empty")
    Integer movieId;

    @NotNull(message = "Person must not be empty")
    Integer personId;

    @NotNull(message = "Role must not be empty")
    PersonRole role;
}
