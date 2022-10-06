//package com.tisv2000.integration;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.transaction.annotation.Transactional;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//@Transactional
//@SpringBootTest
//@Sql({
//        "classpath:sql/data.sql"
//})
//public class IntegrationTestBase {
//
//    // поднимается динамически
//    public static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.1");
//
//    @BeforeAll
//    static void runContainer() {
//        container.start();
//    }
//
//    @DynamicPropertySource
//    static void postgresProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.username", container::getUsername);
//        registry.add("spring.datasource.password", container::getPassword);
//    }
//}
