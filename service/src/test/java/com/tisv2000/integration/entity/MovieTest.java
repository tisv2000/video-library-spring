//package com.tisv2000.integration.entity;
//
//import com.tisv2000.database.entity.Movie;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.Test;
//
//import static com.tisv2000.testUtils.TestUtil.getMovie;
//import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
//import static org.assertj.core.api.Assertions.assertThat;
//
//class MovieTest {
//
//    @Test
//    void saveAndGetMovie() {
//        Movie movie = getMovie();
//
//        try (SessionFactory sessionFactory = buildSessionFactory()) {
//            Session session = sessionFactory.openSession();
//
//            try (session) {
//                session.beginTransaction();
//
//                session.save(movie);
//                // правильно ли тут тоже делать flush и evict? в противном случае у нас же останется в кэше сущность movie
//                session.flush();
//                session.evict(movie);
//
//                Movie createdMovie = session.get(Movie.class, movie.getId());
//                assertThat(createdMovie.getTitle()).isEqualTo(movie.getTitle());
//
//                session.getTransaction().commit();
//            }
//        }
//    }
//
//    @Test
//    void updateMovie() {
//        Movie movie = getMovie();
//        var updatedTitle = "Better title";
//
//        try (SessionFactory sessionFactory = buildSessionFactory()) {
//            Session session = sessionFactory.openSession();
//
//            try (session) {
//                session.beginTransaction();
//
//                session.save(movie);
//
//                movie.setTitle(updatedTitle);
//                session.update(movie);
//                session.flush();
//                session.evict(movie);
//
//                Movie createdMovie = session.get(Movie.class, movie.getId());
//                assertThat(createdMovie.getTitle()).isEqualTo(updatedTitle);
//
//                session.getTransaction().commit();
//            }
//        }
//    }
//
//    @Test
//    void deleteMovie() {
//        Movie movie = getMovie();
//
//        try (SessionFactory sessionFactory = buildSessionFactory()) {
//            Session session = sessionFactory.openSession();
//
//            try (session) {
//                session.beginTransaction();
//
//                session.save(movie);
//                session.delete(movie);
//                session.flush();
//                session.evict(movie);
//
//                Movie deletedMovie = session.get(Movie.class, movie.getId());
//                assertThat(deletedMovie).isNull();
//
//                session.getTransaction().commit();
//            }
//        }
//    }
//}
