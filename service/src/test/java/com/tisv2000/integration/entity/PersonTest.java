package com.tisv2000.integration.entity;

import com.tisv2000.database.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static com.tisv2000.testUtils.TestUtil.getPerson;
import static com.tisv2000.util.HibernateUtil.buildSessionFactory;
import static org.assertj.core.api.Assertions.assertThat;

class PersonTest {

    @Test
    void saveAndGetPerson() {
        Person person = getPerson();

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(person);
                session.flush();
                session.evict(person);

                Person createdPerson = session.get(Person.class, person.getId());
                assertThat(createdPerson.getName()).isEqualTo(person.getName());

                session.getTransaction().commit();
            }
        }
    }

    @Test
    void updatePerson() {
        Person person = getPerson();
        var updatedName = "Better name";

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(person);

                person.setName(updatedName);
                session.update(person);
                session.flush();
                session.evict(person);

                Person updatedPerson = session.get(Person.class, person.getId());
                assertThat(updatedPerson.getName()).isEqualTo(updatedName);

                session.getTransaction().commit();
            }
        }
    }

    @Test
    void deletePerson() {
        Person person = getPerson();

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();

            try (session) {
                session.beginTransaction();

                session.save(person);

                session.delete(person);
                session.flush();
                session.evict(person);

                Person deletedPerson = session.get(Person.class, person.getId());
                assertThat(deletedPerson).isNull();

                session.getTransaction().commit();
            }
        }
    }
}
