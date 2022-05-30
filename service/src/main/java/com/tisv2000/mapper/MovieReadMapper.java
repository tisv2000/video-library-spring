package com.tisv2000.mapper;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.dto.MovieReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieReadMapper implements Mapper<Movie, MovieReadDto>{
    @Override
    public MovieReadDto map(Movie object) {
        return new MovieReadDto(
                object.getId(),
                object.getTitle(),
                object.getYear(),
                object.getDescription(),
                object.getCountry(),
                object.getGenre(),
                object.getImage(),
                object.getReviews()
        );
    }
}
