package com.tisv2000.integration;

import com.tisv2000.database.entity.Gender;
import com.tisv2000.database.entity.Genre;
import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.Review;
import com.tisv2000.database.entity.Role;
import com.tisv2000.database.entity.User;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@UtilityClass
public class TestDataImporter {

    public void importTestData(EntityManager entityManager) {
            User user1 = saveUser(entityManager, Role.USER, Gender.MALE, LocalDate.of(2000, 1, 1),
                    "Tomas", "123", "tom@gmail.com");

            User user2 = saveUser(entityManager, Role.USER, Gender.MALE, LocalDate.of(1990, 1, 1),
                    "Bill", "456", "bill@gmail.com");

            Movie titanicMovie = saveMovie(entityManager, Genre.DRAMA, "Titanic",
                    1997, "the USA", "la-la-la");
            Movie holidayMovie = saveMovie(entityManager, Genre.DRAMA, "The Holiday",
                    2006, "the USA", "some text");
            Movie vampireDiariesMovie = saveMovie(entityManager, Genre.THRILLER, "The Vampire Diaries",
                    2009, "the USA", "text");

            Review holidayReview1 = saveReview(entityManager, user1, holidayMovie, "good movie", 8);
            Review holidayReview2 = saveReview(entityManager, user2, holidayMovie, "i like it very much", 10);
            Review vampireDiariesReview1 = saveReview(entityManager, user1, vampireDiariesMovie, "best movie ever", 10);
    }

    private User saveUser(EntityManager entityManager, Role role, Gender gender, LocalDate birthdate, String name, String password, String email) {
        User user = User.builder()
                .role(role)
                .gender(gender)
                .birthdate(birthdate)
                .name(name)
                .password(password)
                .email(email)
                .build();
        entityManager.persist(user);
        return user;
    }

    private Movie saveMovie(EntityManager entityManager, Genre genre, String title, Integer year, String country, String description) {
        Movie movie = Movie.builder()
                .genre(genre)
                .title(title)
                .year(year)
                .country(country)
                .description(description)
                .build();
        entityManager.persist(movie);
        return movie;
    }

    private Review saveReview(EntityManager entityManager, User user, Movie movie, String text, Integer rate) {
        Review review = Review.builder()
                .user(user)
                .movie(movie)
                .text(text)
                .rate(rate)
                .build();
        entityManager.persist(review);
        return review;
    }
}
