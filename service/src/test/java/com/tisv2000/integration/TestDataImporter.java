package com.tisv2000.integration;

import com.tisv2000.entity.*;
import lombok.Cleanup;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

@UtilityClass
public class TestDataImporter {

    public void importTestData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();

        User user1 = saveUser(session, Role.USER, Gender.MALE, LocalDate.of(2000, 1, 1),
                "Tomas", "123", "tom@gmail.com");

        User user2 = saveUser(session, Role.USER, Gender.MALE, LocalDate.of(1990, 1, 1),
                "Bill", "456", "bill@gmail.com");

        Movie titanicMovie = saveMovie(session, Genre.DRAMA, "Titanic",
                1997, "the USA", "la-la-la");
        Movie holidayMovie = saveMovie(session, Genre.DRAMA, "The Holiday",
                2006, "the USA", "some text");
        Movie vampireDiariesMovie = saveMovie(session, Genre.THRILLER, "The Vampire Diaries",
                2009, "the USA", "text");

        Review holidayReview1 = saveReview(session, user1, holidayMovie, "good movie", 8);
        Review holidayReview2 = saveReview(session, user2, holidayMovie, "i like it very much", 10);
        Review vampireDiariesReview1 = saveReview(session, user1, vampireDiariesMovie, "best movie ever", 10);
    }

    private User saveUser(Session session, Role role, Gender gender, LocalDate birthdate, String name, String password, String email) {
        User user = User.builder()
                .role(role)
                .gender(gender)
                .birthdate(birthdate)
                .name(name)
                .password(password)
                .email(email)
                .build();
        session.save(user);
        return user;
    }

    private Movie saveMovie(Session session, Genre genre, String title, Integer year, String country, String description) {
        Movie movie = Movie.builder()
                .genre(genre)
                .title(title)
                .year(year)
                .country(country)
                .description(description)
                .build();
        session.save(movie);
        return movie;
    }

    private Review saveReview(Session session, User user, Movie movie, String text, Integer rate) {
        Review review = Review.builder()
                .user(user)
                .movie(movie)
                .text(text)
                .rate(rate)
                .build();
        session.save(review);
        return review;
    }
}
