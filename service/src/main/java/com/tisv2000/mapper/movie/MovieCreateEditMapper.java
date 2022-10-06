package com.tisv2000.mapper.movie;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.dto.movie.MovieCreateEditDto;
import com.tisv2000.mapper.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

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
        movie.setTitle(object.getNewTitle());
        movie.setYear(object.getNewYear());
        movie.setDescription(object.getNewDescription());
        movie.setCountry(object.getNewCountry());
        movie.setGenre(object.getNewGenre());

        Optional.ofNullable(object.getNewImage())
                .filter(image -> !image.isEmpty())
                .ifPresent(image -> movie.setImage(image.getOriginalFilename()));
    }
}
