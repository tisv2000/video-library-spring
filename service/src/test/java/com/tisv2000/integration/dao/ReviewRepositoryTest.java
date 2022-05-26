package com.tisv2000.integration.dao;

import com.tisv2000.database.repository.MovieRepository;
import com.tisv2000.database.repository.ReviewRepository;
import com.tisv2000.database.repository.UserRepository;
import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.Review;
import com.tisv2000.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.tisv2000.testUtils.TestUtil.getMovie;
import static com.tisv2000.testUtils.TestUtil.getReview;
import static com.tisv2000.testUtils.TestUtil.getUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor
@SpringBootTest
@Transactional
public class ReviewRepositoryTest {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Test
    void saveAndFindById() {
        User user = getUser();
        Movie movie = getMovie();
        Review review = getReview(user, movie);

        reviewRepository.save(review);

        var maybeReview = reviewRepository.findById(review.getId());

        assertThat(maybeReview).isPresent();
        assertThat(maybeReview.get().getRate()).isEqualTo(review.getRate());
    }

    @Test
    void updateTest() {
        User user = getUser();
        Movie movie = getMovie();
        Review review = getReview(user, movie);

        userRepository.save(user);
        movieRepository.save(movie);
        reviewRepository.save(review);

        var maybeReviewToUpdate = reviewRepository.findById(review.getId());
        assertThat(maybeReviewToUpdate).isPresent();
        var reviewToUpdate = maybeReviewToUpdate.get();
        reviewToUpdate.setRate(10);
        reviewRepository.saveAndFlush(reviewToUpdate);

        var maybeUpdatedReview = reviewRepository.findById(reviewToUpdate.getId());

        assertThat(maybeUpdatedReview).isPresent();
        assertThat(maybeUpdatedReview.get().getRate()).isEqualTo(reviewToUpdate.getRate());
    }

    @Test
    void deleteTest() {
        User user = getUser();
        Movie movie = getMovie();
        Review review = getReview(user, movie);

        reviewRepository.save(review);

        var maybeReviewToDelete = reviewRepository.findById(review.getId());
        assertThat(maybeReviewToDelete).isPresent();
        reviewRepository.deleteById(maybeReviewToDelete.get().getId());

        assertThat(reviewRepository.findById(maybeReviewToDelete.get().getId())).isEmpty();
    }
}
