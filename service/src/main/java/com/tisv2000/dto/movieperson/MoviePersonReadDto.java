package com.tisv2000.dto.movieperson;

import com.tisv2000.database.entity.PersonRole;
import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.dto.person.PersonReadDto;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MoviePersonReadDto {
    MovieReadDto movie;
    PersonReadDto person;
    PersonRole role;
}
