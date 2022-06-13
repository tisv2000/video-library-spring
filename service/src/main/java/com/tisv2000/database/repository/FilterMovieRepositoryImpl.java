package com.tisv2000.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.QMovie;
import com.tisv2000.database.querydsl.QPredicates;
import com.tisv2000.dto.movie.MovieFilterDto;

import java.util.List;

import static com.tisv2000.database.entity.QMovie.movie;

public class FilterMovieRepositoryImpl implements FilterMovieRepository {

    @Override
    public List<Movie> findAllByFilter(MovieFilterDto movieFilterDto) {
        var predicate = QPredicates.builder()
                .add(movieFilterDto.getTitle(), movie.country::containsIgnoreCase)
                .add(movieFilterDto.getYear().toString(), movie.country::eq)
                .add(movieFilterDto.getCountry(), movie.country::containsIgnoreCase)
                .add(movieFilterDto.getGenre().toString(), movie.country::containsIgnoreCase)
                .build();

        return new JPAQuery<Movie>()
                .select(movie)
                .from(movie)
                .where(predicate)
                .fetch();
    }
}
