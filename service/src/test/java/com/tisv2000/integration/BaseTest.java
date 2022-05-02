package com.tisv2000.integration;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.tisv2000.util.HibernateUtil.buildSessionFactory;

public class BaseTest {

    // TODO плохая идея делать sessionFactory public static?
    public static final SessionFactory sessionFactory = buildSessionFactory();

    @BeforeAll
    void initDataBase() {
        TestDataImporter.importTestData(sessionFactory);
    }

    @AfterAll
    public void cleanUp() {
        sessionFactory.close();
    }

}
