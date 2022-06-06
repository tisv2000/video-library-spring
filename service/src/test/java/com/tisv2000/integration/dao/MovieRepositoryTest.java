package com.tisv2000.integration.dao;

import com.tisv2000.database.entity.Genre;
import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.repository.MovieRepository;
import com.tisv2000.dto.movie.MovieFilterDto;
import com.tisv2000.integration.IntegrationTestBase;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static com.tisv2000.testUtils.TestUtil.getMovie;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@Transactional
public class MovieRepositoryTest extends IntegrationTestBase {

    private final MovieRepository movieRepository;

    @Test
    void saveAndFindById() {
        Movie movie = getMovie();

        movieRepository.save(movie);

        var maybeReview = movieRepository.findById(movie.getId());

        assertThat(maybeReview).isPresent();
        assertThat(maybeReview.get().getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    void updateTest() {
        Movie movie = getMovie();

        movieRepository.save(movie);

        var maybeReviewToUpdate = movieRepository.findById(movie.getId());
        assertThat(maybeReviewToUpdate).isPresent();
        var movieToUpdate = maybeReviewToUpdate.get();
        movieToUpdate.setTitle("New title");
        movieRepository.saveAndFlush(movieToUpdate);

        var maybeUpdatedMovie = movieRepository.findById(movieToUpdate.getId());

        assertThat(maybeUpdatedMovie).isPresent();
        assertThat(maybeUpdatedMovie.get().getTitle()).isEqualTo(movieToUpdate.getTitle());
    }

    @Test
    void deleteTest() {
        Movie movie = getMovie();

        movieRepository.save(movie);

        var maybeMovieToDelete = movieRepository.findById(movie.getId());
        assertThat(maybeMovieToDelete).isPresent();
        movieRepository.deleteById(maybeMovieToDelete.get().getId());

        assertThat(movieRepository.findById(maybeMovieToDelete.get().getId())).isEmpty();
    }

//    @ParameterizedTest
//    @MethodSource("movieFilterDataProvider")
//    void findAllByFilterTest(MovieFilterDto movieFilterDto, int expectedResult) {
//        List<Movie> filteredMovies = new MovieRepository(entityManager).findAllByFilterQueryDsl(movieFilterDto);
//        assertThat(filteredMovies.size()).isEqualTo(expectedResult);
//    }
//
//    @ParameterizedTest
//    @MethodSource("movieFilterDataProvider")
//    void findAllByFilterCriteriaApiTest(MovieFilterDto movieFilterDto, int expectedResult) {
//        List<Movie> filteredMovies = new MovieRepository(entityManager).findAllByFilterCriteriaApi(movieFilterDto);
//        assertThat(filteredMovies.size()).isEqualTo(expectedResult);
//    }

    public static Stream<Arguments> movieFilterDataProvider() {
        return Stream.of(
                Arguments.of(buildMovieFilterDto("The Holiday", 2006, "the USA", Genre.DRAMA), 1),
                Arguments.of(buildMovieFilterDto("", 2006, "the USA", Genre.DRAMA), 1),
                Arguments.of(buildMovieFilterDto("The Holiday", null, "the USA", Genre.DRAMA), 1),
                Arguments.of(buildMovieFilterDto("The Holiday", 2006, "", Genre.DRAMA), 1),
                Arguments.of(buildMovieFilterDto("The Holiday", 2006, "the USA", null), 1),
                Arguments.of(buildMovieFilterDto(null, 2006, "the USA", null), 1)
        );
    }

    private static MovieFilterDto buildMovieFilterDto(String title, Integer year, String country, Genre genre) {
        return MovieFilterDto.builder()
                .title(title)
                .year(year)
                .country(country)
                .genre(genre)
                .build();
    }
}
