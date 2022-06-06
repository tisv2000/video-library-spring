package com.tisv2000.mapper.movie;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.dto.movie.MovieCreateEditDto;
import com.tisv2000.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MovieCreateEditMapper implements Mapper<MovieCreateEditDto, Movie> {

    @Override
    public Movie map(MovieCreateEditDto fromObject, Movie toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Movie map(MovieCreateEditDto object) {
        Movie movie = new Movie();
        copy(object, movie);
        return movie;
    }

    private void copy(MovieCreateEditDto object, Movie movie) {
        movie.setTitle(object.getTitle());
        movie.setYear(object.getYear());
        movie.setDescription(object.getDescription());
        movie.setCountry(object.getCountry());
        movie.setGenre(object.getGenre());
        movie.setImage(object.getImage());
    }
}
