package com.tisv2000.integration.entity;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.MoviePerson;
import com.tisv2000.database.entity.Person;
import com.tisv2000.database.entity.PersonRole;
import com.tisv2000.testUtils.TestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static com.tisv2000.testUtils.TestUtil.getMovie;
import static com.tisv2000.testUtils.TestUtil.getPerson;
import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

class MoviePersonTest {

    @Test
    void saveAndGetMoviePerson() {
        Movie movie = getMovie();
        Person person = getPerson();
        MoviePerson moviePerson = TestUtil.getMoviePerson(movie, person);

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(movie);
                session.save(person);
                session.save(moviePerson);
                session.flush();
                session.evict(moviePerson);

                MoviePerson createdMoviePerson = session.get(MoviePerson.class, moviePerson.getId());
                assertThat(createdMoviePerson.getRole()).isEqualTo(moviePerson.getRole());

                session.getTransaction().commit();
            }
        }
    }

    @Test
    void updateMoviePerson() {
        Movie movie = getMovie();
        Person person = getPerson();
        MoviePerson moviePerson = TestUtil.getMoviePerson(movie, person);
        var updatedPersonRole = PersonRole.DIRECTOR;

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(movie);
                session.save(person);
                session.save(moviePerson);

                moviePerson.setRole(updatedPersonRole);
                session.update(moviePerson);
                session.flush();
                session.evict(moviePerson);

                MoviePerson updatedMoviePersonRole = session.get(MoviePerson.class, moviePerson.getId());
                assertThat(updatedMoviePersonRole.getRole()).isEqualTo(updatedPersonRole);

                session.getTransaction().commit();
            }
        }
    }

    @Test
    void deleteMoviePerson() {
        Movie movie = getMovie();
        Person person = getPerson();
        MoviePerson moviePerson = TestUtil.getMoviePerson(movie, person);

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(movie);
                session.save(person);
                session.save(moviePerson);

                session.delete(moviePerson);
                session.flush();
                session.evict(moviePerson);

                MoviePerson deletedMoviePerson = session.get(MoviePerson.class, moviePerson.getId());
                assertThat(deletedMoviePerson).isNull();

                session.getTransaction().commit();
            }
        }
    }
}
