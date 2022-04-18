package com.tisv2000.integration.entity;

import com.tisv2000.entity.User;
import com.tisv2000.testUtils.TestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    public void saveAndGetUser() {
        User user = TestUtil.user;

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);
                session.flush();
                session.evict(user);

                User createdUser = session.get(User.class, user.getId());
                assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());

                session.getTransaction().commit();
            }
        }
    }

    @Test
    public void updateUser() {
        User user = TestUtil.user;
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

                User updatedUser = session.get(User.class, user.getId());

                assertThat(updatedUser.getEmail()).isEqualTo(updatedEmail);
                session.getTransaction().commit();
            }
        }
    }

    @Test
    public void deleteUser() {
        User user = TestUtil.user;
        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(user);

                // почему-то не вижу в сформированных sql запросах get запрос перед delete, сначала же он выполняется, а потом уже delete?
                session.delete(user);
                // следовательно не понятно, почему тест проходит если мы не делаем flush и evict этой сущности, у нас
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
