package com.tisv2000.mapper.movieperson;

import com.tisv2000.database.entity.MoviePerson;
import com.tisv2000.database.repository.MovieRepository;
import com.tisv2000.database.repository.PersonRepository;
import com.tisv2000.dto.movieperson.MoviePersonCreateEditDto;
import com.tisv2000.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoviePersonCreateEditMapper implements Mapper<MoviePersonCreateEditDto, MoviePerson> {

    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    @Override
    public MoviePerson map(MoviePersonCreateEditDto object) {
        return MoviePerson.builder()
                .movie(
                        movieRepository
                                .findById(object.getMovieId())
                                .orElseThrow()
                )
                .person(
                        personRepository
                                .findById(object.getPersonId())
                                .orElseThrow()
                )
                .role(object.getRole())
                .build();
    }

}
