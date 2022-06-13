package com.tisv2000.integration.service;

import com.tisv2000.database.entity.Genre;
import com.tisv2000.dto.movie.MovieCreateEditDto;
import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.integration.IntegrationTestBase;
import com.tisv2000.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
class MovieServiceIT extends IntegrationTestBase {

    private static final Integer MOVIE_1 = 1;
    private final MovieService movieService;

    @Test
    void findAll() {
        List<MovieReadDto> result = movieService.findAll();
        assertThat(result).hasSize(3);
    }

    @Test
    void findById() {
        var result = movieService.findById(MOVIE_1);
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("The Vampire Diaries");
    }

    @Test
    void create() {
        var newMovie = new MovieCreateEditDto(
                "New movie",
                1990,
                "Some text",
                "Wonderland",
                Genre.ADVENTURE,
                "image"
        );
        var createdMovie = movieService.create(newMovie);
        assertThat(createdMovie.getTitle()).isEqualTo(newMovie.getTitle());
    }

    @Test
    void update() {
        var newMovieForUpdate = new MovieCreateEditDto(
                "New movie",
                1990,
                "Some text",
                "Wonderland",
                Genre.ADVENTURE,
                "image"
        );

        var actualResult = movieService.update(MOVIE_1, newMovieForUpdate);
        assertThat(actualResult).isPresent();
        actualResult.ifPresent(movie -> {
                    assertThat(movie.getTitle()).isEqualTo(newMovieForUpdate.getTitle());
                    assertThat(movie.getYear()).isEqualTo(newMovieForUpdate.getYear());
                    assertThat(movie.getDescription()).isEqualTo(newMovieForUpdate.getDescription());
                    assertThat(movie.getCountry()).isEqualTo(newMovieForUpdate.getCountry());
                    assertThat(movie.getGenre()).isEqualTo(newMovieForUpdate.getGenre());
                    assertThat(movie.getImage()).isEqualTo(newMovieForUpdate.getImage());
                }
        );
    }

    @Test
    void delete() {
        assertFalse(movieService.delete(999));
        assertTrue(movieService.delete(MOVIE_1));
    }
}
