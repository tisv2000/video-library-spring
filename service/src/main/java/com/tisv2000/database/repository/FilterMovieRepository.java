package com.tisv2000.database.repository;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.dto.movie.MovieFilterDto;

import java.util.List;

public interface FilterMovieRepository {

    List<Movie> findAllByFilter(MovieFilterDto movieFilterDto);

}
