//package com.tisv2000.testUtils;
//
//import com.tisv2000.database.entity.Gender;
//import com.tisv2000.database.entity.Genre;
//import com.tisv2000.database.entity.Movie;
//import com.tisv2000.database.entity.MoviePerson;
//import com.tisv2000.database.entity.Person;
//import com.tisv2000.database.entity.PersonRole;
//import com.tisv2000.database.entity.Review;
//import com.tisv2000.database.entity.Role;
//import com.tisv2000.database.entity.User;
//import lombok.experimental.UtilityClass;
//
//import java.time.LocalDate;
//
//@UtilityClass
//public class TestUtil {
//
//    public static Person getPerson() {
//        return Person.builder()
//                .name("John")
//                .birthday(LocalDate.of(2000, 1, 1))
//                .build();
//    }
//
//    public static User getUser() {
//        return User.builder()
//                .name("Leo")
//                .birthdate(LocalDate.of(2000, 1, 1))
//                .password("123")
//                .email("leo@test.com")
//                .role(Role.USER)
//                .gender(Gender.MALE)
//                .build();
//    }
//
//    public static Movie getMovie() {
//        return Movie.builder()
//                .title("Test movie")
//                .year(2000)
//                .country("Wonderland")
//                .genre(Genre.THRILLER)
//                .description("la la la")
//                .build();
//    }
//
//    public static Review getReview(User user, Movie movie) {
//        return Review.builder()
//                .movie(movie)
//                .user(user)
//                .text("some text")
//                .rate(5)
//                .build();
//    }
//
//    public static MoviePerson getMoviePerson(Movie movie, Person person) {
//        return MoviePerson.builder()
//                .movie(movie)
//                .person(person)
//                .role(PersonRole.ACTOR)
//                .build();
//    }
//}
