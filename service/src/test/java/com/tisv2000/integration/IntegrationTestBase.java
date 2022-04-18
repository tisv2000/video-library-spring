//package com.tisv2000.integration;
//
//import com.tisv2000.util.HibernateUtil;
//import lombok.SneakyThrows;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.junit.jupiter.api.BeforeEach;
//
//public abstract class IntegrationTestBase {
//
//    private static final String CLEAN_SQL = "DROP TABLE IF EXISTS users;";
//    private static final String CREATE_SQL = """
//            CREATE TABLE IF NOT EXISTS users
//            (
//                id INT AUTO_INCREMENT PRIMARY KEY ,
//                name VARCHAR(256),
//                birthday DATE NOT NULL ,
//                email VARCHAR(256) NOT NULL UNIQUE ,
//                password VARCHAR(256) NOT NULL ,
//                role VARCHAR(32) NOT NULL ,
//                gender VARCHAR(32)
//            );
//            """;
//    private static final String INSERT_SQL = """
//            INSERT INTO users (name, birthday, email, password, role, gender)
//            VALUES ('John', '2000-01-01', 'john@gmail.com', '123', 'ADMIN', 'MALE'),
//                   ('Emily', '2002-05-05', 'emily@gmail.com', '555', 'USER', 'FEMAILE'),
//                   ('Tomas', '1999-12-25', 'tomas@gmail.com', '853', 'USER', 'MALE'),
//                   ('Ronald', '1995-09-09', 'ronald@gmail.com', '909', 'USER', 'MALE'),
//            """;
//
//    @BeforeEach
//    @SneakyThrows
//    void prepareDatabase() {
//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
//            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//
////            Не получилось использовать этот класс:(( Почему-то он пытается создать все таблицы и падает с ошибкой...
//            session.createQuery(CLEAN_SQL);
//            session.createQuery(CREATE_SQL);
//            session.createQuery(INSERT_SQL);
//
//            session.getTransaction().commit();
//        }
//    }
//}
