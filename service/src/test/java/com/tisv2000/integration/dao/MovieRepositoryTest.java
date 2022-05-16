package com.tisv2000.integration.dao;

import com.tisv2000.dao.MovieRepository;
import com.tisv2000.dto.MovieFilterDto;
import com.tisv2000.entity.Movie;
import com.tisv2000.integration.TestDataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Stream;

import static com.tisv2000.testUtils.TestUtil.getMovie;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class MovieRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void initDataBase() {
        TestDataImporter.importTestData(entityManager);
    }

    @Test
    void findById() {
        Movie movie = getMovie();

        entityManager.persist(movie);

        // TODO почему возвращается не optional?
        var foundMovie = entityManager.find(Movie.class, movie.getId());

        assertNotNull(foundMovie);
        assertThat(foundMovie.getTitle()).isEqualTo("Test movie");
    }

    @Test
    void saveTest() {
        Movie movie = getMovie();

        entityManager.persist(movie);

        var savedMovie = entityManager.find(Movie.class, movie.getId());

        assertThat(savedMovie.getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    void updateTest() {

        Movie movie = getMovie();

        entityManager.persist(movie);

        var movieToUpdate = entityManager.find(Movie.class, movie.getId());
        movieToUpdate.setTitle("New title");
        entityManager.merge(movieToUpdate);

        var updatedMovie = entityManager.find(Movie.class, movieToUpdate.getId());

        assertNotNull(updatedMovie);
        assertThat(updatedMovie.getTitle()).isEqualTo(movieToUpdate.getTitle());
    }

    @Test
    void deleteTest() {
        Movie movie = getMovie();

        entityManager.persist(movie);

        var movieToDelete = entityManager.find(Movie.class, movie.getId());
        entityManager.remove(movieToDelete);

        assertThat(entityManager.find(Movie.class, movieToDelete.getId())).isNull();
    }

    @ParameterizedTest
    @MethodSource("movieFilterDataProvider")
    void findAllByFilterTest(MovieFilterDto movieFilterDto, int expectedResult) {
        List<Movie> filteredMovies = new MovieRepository(entityManager).findAllByFilterQueryDsl(movieFilterDto);
        assertThat(filteredMovies.size()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("movieFilterDataProvider")
    void findAllByFilterCriteriaApiTest(MovieFilterDto movieFilterDto, int expectedResult) {
        List<Movie> filteredMovies = new MovieRepository(entityManager).findAllByFilterCriteriaApi(movieFilterDto);
        assertThat(filteredMovies.size()).isEqualTo(expectedResult);
    }

    public static Stream<Arguments> movieFilterDataProvider() {
        return Stream.of(
                Arguments.of(buildMovieFilterDto("The Holiday", "", "", ""), 1),
                Arguments.of(buildMovieFilterDto("The Holiday", "2006", "the USA", "DRAMA"), 1),
                Arguments.of(buildMovieFilterDto("", "2006", "the USA", "DRAMA"), 1),
                Arguments.of(buildMovieFilterDto("The Holiday", "", "the USA", "DRAMA"), 1),
                Arguments.of(buildMovieFilterDto("The Holiday", "2006", "", "DRAMA"), 1),
                Arguments.of(buildMovieFilterDto("The Holiday", "2006", "the USA", ""), 1),
                Arguments.of(buildMovieFilterDto(null, "2006", "the USA", ""), 1)
        );
    }

    private static MovieFilterDto buildMovieFilterDto(String title, String year, String country, String genre) {
        return MovieFilterDto.builder()
                .title(title)
                .year(year)
                .country(country)
                .genre(genre)
                .build();
    }
}
