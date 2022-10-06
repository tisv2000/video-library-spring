package com.tisv2000.mapper.movieperson;

import com.tisv2000.database.entity.MoviePerson;
import com.tisv2000.dto.movieperson.MoviePersonReadDto;
import com.tisv2000.mapper.Mapper;
import com.tisv2000.mapper.movie.MovieReadMapper;
import com.tisv2000.mapper.person.PersonReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoviePersonReadMapper implements Mapper<MoviePerson, MoviePersonReadDto> {

    private final MovieReadMapper movieReadMapper;
    private final PersonReadMapper personReadMapper;

    @Override
    public MoviePersonReadDto map(MoviePerson object) {
        return MoviePersonReadDto.builder()
                .movie(movieReadMapper.map(object.getMovie()))
                .person(personReadMapper.map(object.getPerson()))
                .role(object.getRole())
                .build();
    }
}
