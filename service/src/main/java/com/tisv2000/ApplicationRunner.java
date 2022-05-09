package com.tisv2000;

import com.tisv2000.config.ApplicationContext;
import com.tisv2000.dao.MovieRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {

        try(var context = new AnnotationConfigApplicationContext(ApplicationContext.class)) {
            var bean = context.getBean(MovieRepository.class);
            System.out.println(bean);
        }

    }

}
