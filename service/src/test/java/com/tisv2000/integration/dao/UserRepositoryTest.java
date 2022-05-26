package com.tisv2000.integration.dao;

import com.tisv2000.database.repository.UserRepository;
import com.tisv2000.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.tisv2000.testUtils.TestUtil.getUser;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void saveAndFindById() {
        User user = getUser();

        userRepository.save(user);

        var maybeReview = userRepository.findById(user.getId());

        assertThat(maybeReview).isPresent();
        assertThat(maybeReview.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void updateTest() {
        User user = getUser();
        var newEmail = "fancy+email@test.test";

        userRepository.save(user);

        var maybeReviewToUpdate = userRepository.findById(user.getId());
        assertThat(maybeReviewToUpdate).isPresent();
        var userToUpdate = maybeReviewToUpdate.get();
        userToUpdate.setEmail(newEmail);
        userRepository.saveAndFlush(userToUpdate);

        var maybeUpdatedReview = userRepository.findById(userToUpdate.getId());

        assertThat(maybeUpdatedReview).isPresent();
        assertThat(maybeUpdatedReview.get().getEmail()).isEqualTo(userToUpdate.getEmail());
    }

    @Test
    void deleteTest() {
        User user = getUser();

        userRepository.save(user);

        var maybeReviewToDelete = userRepository.findById(user.getId());
        assertThat(maybeReviewToDelete).isPresent();
        userRepository.deleteById(maybeReviewToDelete.get().getId());

        assertThat(userRepository.findById(maybeReviewToDelete.get().getId())).isEmpty();
    }
}
