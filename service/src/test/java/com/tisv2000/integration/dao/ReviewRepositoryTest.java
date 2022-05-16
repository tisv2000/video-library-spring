package com.tisv2000.integration.dao;

import com.tisv2000.entity.Movie;
import com.tisv2000.entity.Review;
import com.tisv2000.entity.User;
import com.tisv2000.integration.TestDataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.tisv2000.testUtils.TestUtil.getMovie;
import static com.tisv2000.testUtils.TestUtil.getReview;
import static com.tisv2000.testUtils.TestUtil.getUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class ReviewRepositoryTest {

    @Autowired
    private EntityManager entityManager;

//    @BeforeEach
//    void initDataBase() {
//        TestDataImporter.importTestData(entityManager);
//    }

    @Test
    void findById() {
        User user = getUser();
        Movie movie = getMovie();
        Review review = getReview(user, movie);

        entityManager.persist(review);

        var foundReview = entityManager.find(Review.class, review.getId());

        assertNotNull(foundReview);
        assertThat(foundReview.getRate()).isEqualTo(review.getRate());
    }

    @Test
    void saveTest() {
        User user = getUser();
        Movie movie = getMovie();
        Review review = getReview(user, movie);

        entityManager.persist(review);

        var savedMovie = entityManager.find(Review.class, review.getId());

        assertThat(savedMovie.getRate()).isEqualTo(review.getRate());
    }

    @Test
    void updateTest() {
        User user = getUser();
        Movie movie = getMovie();
        Review review = getReview(user, movie);

        entityManager.persist(review);

        var reviewToUpdate = entityManager.find(Review.class, review.getId());
        reviewToUpdate.setRate(10);
        entityManager.merge(reviewToUpdate);

        var updatedReview = entityManager.find(Review.class, reviewToUpdate.getId());

        assertNotNull(updatedReview);
        assertThat(updatedReview.getRate()).isEqualTo(reviewToUpdate.getRate());
    }

    @Test
    void deleteTest() {
        User user = getUser();
        Movie movie = getMovie();
        Review review = getReview(user, movie);

        entityManager.persist(review);

        var reviewToDelete = entityManager.find(Review.class, review.getId());
        entityManager.remove(reviewToDelete);

        assertThat(entityManager.find(Review.class, reviewToDelete.getId())).isNull();
    }

}
