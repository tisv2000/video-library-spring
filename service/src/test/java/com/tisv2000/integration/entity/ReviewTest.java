package com.tisv2000.integration.entity;

import com.tisv2000.entity.Movie;
import com.tisv2000.entity.Review;
import com.tisv2000.entity.User;
import com.tisv2000.testUtils.TestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static com.tisv2000.testUtils.TestUtil.getMovie;
import static com.tisv2000.testUtils.TestUtil.getUser;
import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

class ReviewTest {

    @Test
    void saveAndGetReview() {
        Movie movie = getMovie();
        User user = getUser();
        Review review = TestUtil.getReview(user, movie);

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);
                session.save(movie);

                session.save(review);
                session.flush();
                session.evict(movie);

                Review createdReview = session.get(Review.class, review.getId());
                assertThat(createdReview.getRate()).isEqualTo(review.getRate());

                session.getTransaction().commit();
            }
        }
    }

    @Test
    void updateReview() {
        Movie movie = getMovie();
        User user = getUser();
        Review review = TestUtil.getReview(user, movie);
        var updatedRate = 10;

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);
                session.save(movie);
                session.save(review);

                review.setRate(updatedRate);
                session.update(review);
                session.flush();
                session.evict(review);

                Review updatedReview = session.get(Review.class, review.getId());

                assertThat(updatedReview.getRate()).isEqualTo(updatedRate);

                session.getTransaction().commit();
            }
        }
    }

    @Test
    void deleteReview() {
        Movie movie = getMovie();
        User user = getUser();
        Review review = TestUtil.getReview(user, movie);
        var updatedRate = 10;

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);
                session.save(movie);
                session.save(review);

                session.delete(review);
                session.flush();
                session.evict(review);

                Review deletedReview = session.get(Review.class, review.getId());

                assertThat(deletedReview).isNull();

                session.getTransaction().commit();
            }
        }
    }
}
