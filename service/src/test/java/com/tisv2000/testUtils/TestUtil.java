package com.tisv2000.testUtils;

import com.tisv2000.entity.*;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TestUtil {

    public static Person person = Person.builder()
            .name("John")
            .birthday(LocalDate.of(2000, 1, 1))
            .build();

    public static User user = User.builder()
            .name("Leo")
            .birthdate(LocalDate.of(2000, 1, 1))
            .password("123")
            .email("leo@test.com")
            .role(Role.USER)
            .gender(Gender.MALE)
            .build();

    public static Movie movie = Movie.builder()
            .title("Test movie")
            .year(2000)
            .country("Wonderland")
            .genre(Genre.THRILLER)
            .description("la la la")
            .build();

    public static Review getReview(User user, Movie movie) {
        return Review.builder()
                .movie(movie)
                .user(user)
                .text("some text")
                .rate(5)
                .build();
    }

    public static MoviePerson getMoviePerson(Movie movie, Person person) {
        return MoviePerson.builder()
                .movie(movie)
                .person(person)
                .role(PersonRole.ACTOR)
                .build();
    }
}
