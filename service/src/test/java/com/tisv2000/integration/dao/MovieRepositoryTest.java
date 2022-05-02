package com.tisv2000.integration.dao;

import com.tisv2000.dao.MovieRepository;
import com.tisv2000.dto.MovieFilterDto;
import com.tisv2000.entity.Movie;
import com.tisv2000.integration.BaseTest;
import com.tisv2000.integration.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Stream;

import static com.tisv2000.testUtils.TestUtil.getMovie;
import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

class MovieRepositoryTest {

    public static final SessionFactory sessionFactory = buildSessionFactory();

    @BeforeAll
    static void initDataBase() {
        TestDataImporter.importTestData(sessionFactory);
    }

    @AfterAll
    static public void cleanUp() {
        sessionFactory.close();
    }

    @Test
    void saveTest() {
        Movie movie = getMovie();

        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var movieRepository = new MovieRepository(session);

        var savedMovie = movieRepository.save(movie);

        assertThat(savedMovie.getTitle()).isEqualTo(movie.getTitle());
        session.getTransaction().rollback();
    }

    @Test
    void findByIdTest() {
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var movieRepository = new MovieRepository(session);

        var foundMovie = movieRepository.findById(1);

        assertThat(foundMovie).isPresent();
        assertThat(foundMovie.get().getTitle()).isEqualTo("Titanic");

        session.getTransaction().rollback();
    }

    @Test
    void updateTest() {
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var movieRepository = new MovieRepository(session);

        var movieToUpdate = movieRepository.findById(1).get();
        movieToUpdate.setTitle("New title");

        movieRepository.update(movieToUpdate);

        var updatedMovie = movieRepository.findById(1);

        assertThat(updatedMovie).isPresent();
        assertThat(updatedMovie.get().getTitle()).isEqualTo(movieToUpdate.getTitle());

        session.getTransaction().rollback();
    }

    @Test
    void deleteTest() {
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var movieRepository = new MovieRepository(session);

        movieRepository.delete(1);

        assertThat(movieRepository.findById(1)).isEmpty();

        session.getTransaction().rollback();
    }

    @Test
    void findAllTest() {
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var movieRepository = new MovieRepository(session);

        var movies = movieRepository.findAll();

        assertThat(movies.size()).isEqualTo(3);

        session.getTransaction().rollback();
    }

    @ParameterizedTest
    @MethodSource("movieFilterDataProvider")
    void findAllByFilterTest(MovieFilterDto movieFilterDto, int expectedResult) {

        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        List<Movie> filteredMovies = new MovieRepository(session).findAllByFilterQueryDsl(movieFilterDto);

        assertThat(filteredMovies.size()).isEqualTo(expectedResult);

        session.getTransaction().rollback();
    }

    @ParameterizedTest
    @MethodSource("movieFilterDataProvider")
    void findAllByFilterCriteriaApiTest(MovieFilterDto movieFilterDto, int expectedResult) {

        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        List<Movie> filteredMovies = new MovieRepository(session).findAllByFilterCriteriaApi(movieFilterDto);

        assertThat(filteredMovies.size()).isEqualTo(expectedResult);

        session.getTransaction().rollback();

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
