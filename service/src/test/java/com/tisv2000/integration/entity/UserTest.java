package com.tisv2000.integration.entity;

import com.tisv2000.entity.User;
import com.tisv2000.testUtils.TestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static com.tisv2000.testUtils.TestUtil.getUser;
import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void saveAndGetUser() {
        User user = getUser();

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);
                session.flush();
                session.evict(user);

//                User createdUser = session.get(User.class, user.getId());
                var result = session
//                        .createQuery("select u from User u where u.email = ?1", User.class)
//                        .setParameter(1, "leo@test.com")
                        // или
                        .createQuery("select u from User u where u.email = :email", User.class)
                        .setParameter("email", "leo@test.com")
                        .list();


                assertThat(result.get(0).getEmail()).isEqualTo(user.getEmail());

                session.getTransaction().commit();
            }
        }
    }

    @Test
    void updateUser() {
        User user = getUser();
        var updatedEmail = "other@test.com";

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);

                user.setEmail(updatedEmail);
                session.update(user);
                session.flush();
                session.evict(user);

                User getUser = session.get(User.class, user.getId());

                assertThat(getUser.getEmail()).isEqualTo(updatedEmail);
                session.getTransaction().commit();
            }
        }
    }

    @Test
    void deleteUser() {
        User user = getUser();
        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);

                session.delete(user);
                // почему тест проходит если мы не делаем flush и evict этой сущности, у нас
                // же должна в кэше быть сохранена эта сущность и, следовательно, при get запросе сам запрос бы
                // не отправился и вернулась бы эта сущность, которая бы не была еще удаленной и тест должен был упасть...

//                session.flush();
//                session.evict(user);

                User deletedUser = session.get(User.class, user.getId());

                assertThat(deletedUser).isNull();

                session.getTransaction().commit();
            }
        }
    }
}
