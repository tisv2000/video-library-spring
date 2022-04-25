package com.tisv2000.integration.dao;

import com.tisv2000.dao.MovieDao;
import com.tisv2000.dto.MovieFilterDto;
import com.tisv2000.entity.Movie;
import com.tisv2000.entity.Review;
import com.tisv2000.integration.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieDaoTest {

    private final SessionFactory sessionFactory = buildSessionFactory();
    private static final MovieDao movieDao = MovieDao.getInstance();


    @BeforeAll
    void initDataBase() {
        TestDataImporter.importTestData(sessionFactory);
    }

    @AfterAll
    public void cleanUp() {
        sessionFactory.close();
    }

    @ParameterizedTest
    @MethodSource("movieFilterDataProvider")
    void findAllByFilterTest(MovieFilterDto movieFilterDto, int expectedResult) {

        Session session = sessionFactory.openSession();

        try (session) {
            session.beginTransaction();

            List<Movie> filteredMovies = movieDao.findAllByFilterQueryDsl(session, movieFilterDto);
            Review review1 = filteredMovies.get(0).getReviews().get(0);
            session.getTransaction().commit();

            assertThat(filteredMovies.size()).isEqualTo(expectedResult);
            assertThat(filteredMovies.get(0).getTitle()).isEqualTo("The Holiday");
            assertThat(review1.getRate()).isEqualTo(8);
        }
    }

    @ParameterizedTest
    @MethodSource("movieFilterDataProvider")
    void findAllByFilterCriteriaApiTest(MovieFilterDto movieFilterDto, int expectedResult) {

        Session session = sessionFactory.openSession();

        try (session) {
            session.beginTransaction();

            List<Movie> filteredMovies = movieDao.findAllByFilterCriteriaApi(session, movieFilterDto);
            Review review1 = filteredMovies.get(0).getReviews().get(0);
            session.getTransaction().commit();

            assertThat(filteredMovies.size()).isEqualTo(expectedResult);
            assertThat(filteredMovies.get(0).getTitle()).isEqualTo("The Holiday");
            assertThat(review1.getRate()).isEqualTo(8);
        }
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
//                Arguments.of(buildMovieFilterDto("", "", "", "DRAMA"), 2),
//                Arguments.of(buildMovieFilterDto("", "", "", ""), 3),
//                Arguments.of(buildMovieFilterDto("Some test movie", "", "", ""), 0)
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
