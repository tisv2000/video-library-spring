package com.tisv2000.integration.dao;

import com.tisv2000.entity.User;
import com.tisv2000.integration.TestDataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.tisv2000.testUtils.TestUtil.getUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

//    @BeforeEach
//    void initDataBase() {
//        TestDataImporter.importTestData(entityManager);
//    }

    @Test
    void findById() {
        User user = getUser();

        entityManager.persist(user);

        var foundUser = entityManager.find(User.class, user.getId());

        assertNotNull(foundUser);
        assertThat(foundUser.getEmail()).isEqualTo("leo@test.com");
    }

    @Test
    void saveTest() {
        User user = getUser();

        entityManager.persist(user);

        var savedMovie = entityManager.find(User.class, user.getId());

        assertThat(savedMovie.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void updateTest() {
        User user = getUser();
        var newEmail = "fancy+email@test.test";

        entityManager.persist(user);

        var movieToUpdate = entityManager.find(User.class, user.getId());
        movieToUpdate.setEmail(newEmail);
        entityManager.merge(movieToUpdate);

        var updatedMovie = entityManager.find(User.class, movieToUpdate.getId());

        assertNotNull(updatedMovie);
        assertThat(updatedMovie.getEmail()).isEqualTo(newEmail);
    }

    @Test
    void deleteTest() {
        User user = getUser();

        entityManager.persist(user);

        var userToDelete = entityManager.find(User.class, user.getId());
        entityManager.remove(userToDelete);

        assertThat(entityManager.find(User.class, userToDelete.getId())).isNull();
    }

}
