package com.tisv2000.integration.dao;

import com.tisv2000.dao.UserRepository;
import com.tisv2000.entity.User;
import com.tisv2000.integration.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static com.tisv2000.testUtils.TestUtil.getUser;
import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    public static final SessionFactory sessionFactory = buildSessionFactory();

    @BeforeAll
    static void initDataBase() {
        TestDataImporter.importTestData(sessionFactory);
    }

    @AfterAll
    static void cleanUp() {
        sessionFactory.close();
    }

    @Test
    void saveTest() {
        User user = getUser();

        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var userRepository = new UserRepository(session);

        var savedUser = userRepository.save(user);

        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
        session.getTransaction().rollback();
    }

    @Test
    void findByIdTest() {
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var userRepository = new UserRepository(session);

        var foundUser = userRepository.findById(1);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("tom@gmail.com");
        session.getTransaction().rollback();
    }

    @Test
    void updateTest() {
        var newEmail = "new email";
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var userRepository = new UserRepository(session);

        var userToUpdate = userRepository.findById(1).get();
        userToUpdate.setEmail(newEmail);
        userRepository.update(userToUpdate);

        var updatedUser = userRepository.findById(1);

        assertThat(updatedUser).isPresent();
        assertThat(updatedUser.get().getEmail()).isEqualTo(userToUpdate.getEmail());
        session.getTransaction().rollback();
    }

    // TODO падает тест c ConstraintViolationException :(
    @Test
    void deleteTest() {
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var userRepository = new UserRepository(session);

        userRepository.delete(1);

        assertThat(userRepository.findById(1)).isEmpty();

        session.getTransaction().rollback();
    }

    @Test
    void findAllTest() {
        var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        session.beginTransaction();

        var userRepository = new UserRepository(session);

        var movies = userRepository.findAll();

        assertThat(movies.size()).isEqualTo(2);

        session.getTransaction().rollback();
    }

}
