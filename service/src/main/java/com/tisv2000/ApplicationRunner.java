package com.tisv2000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);

        // TODO проанализировать оба варианта
//        try(var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
//            var bean = context.getBean(MovieRepository.class);
//            System.out.println(bean);
//        }
    }
}
