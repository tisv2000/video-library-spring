package com.tisv2000.integration.entity;

import com.tisv2000.entity.*;
import com.tisv2000.testUtils.TestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

class MoviePersonTest {

    @Test
    public void saveAndGetMoviePerson() {
        Movie movie = TestUtil.movie;
        Person person = TestUtil.person;
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
    public void updateMoviePerson() {
        Movie movie = TestUtil.movie;
        Person person = TestUtil.person;
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
    public void deleteMoviePerson() {
        Movie movie = TestUtil.movie;
        Person person = TestUtil.person;
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
