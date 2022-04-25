package com.tisv2000.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.tisv2000.dto.MovieFilterDto;
import com.tisv2000.entity.Genre;
import com.tisv2000.entity.Movie;
import com.tisv2000.entity.Movie_;
import com.tisv2000.entity.QMovie;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDao {

    private static final MovieDao INSTANCE = new MovieDao();
    private static final String HINT_NAME = "javax.persistence.fetchgraph";

    public static MovieDao getInstance() {
        return INSTANCE;
    }

    public List<Movie> findAllByFilterQueryDsl(Session session, MovieFilterDto movieFilterDto) {

        // TODO: Вынести логику создания Predicates в отдельный класс
        List<Predicate> predicates = new ArrayList<>();

        if (movieFilterDto.getTitle() != null && !movieFilterDto.getTitle().isEmpty()) {
            predicates.add(QMovie.movie.title.eq(movieFilterDto.getTitle()));
        }
        if (movieFilterDto.getYear() != null && !movieFilterDto.getYear().isEmpty()) {
            predicates.add(QMovie.movie.year.eq(Integer.valueOf(movieFilterDto.getYear())));
        }
        if (movieFilterDto.getCountry() != null && !movieFilterDto.getCountry().isEmpty()) {
            predicates.add(QMovie.movie.country.eq(movieFilterDto.getCountry()));
        }
        if (movieFilterDto.getGenre() != null && !movieFilterDto.getGenre().isEmpty()) {
            predicates.add(QMovie.movie.genre.eq(Genre.valueOf(movieFilterDto.getGenre())));
        }

        return new JPAQuery<Movie>(session)
                .select(QMovie.movie)
                .from(QMovie.movie)
                .where(predicates.toArray(Predicate[]::new))
                .setHint(HINT_NAME, session.getEntityGraph("WithReviews"))
                .fetch();

    }

    public List<Movie> findAllByFilterCriteriaApi(Session session, MovieFilterDto movieFilterDto) {
        var cb = session.getCriteriaBuilder();

        var criteria = cb.createQuery(Movie.class);

        var movie = criteria.from(Movie.class);

        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();

        if (movieFilterDto.getTitle() != null && !movieFilterDto.getTitle().isEmpty()) {
            predicates.add(cb.equal(movie.get(Movie_.title), movieFilterDto.getTitle()));
        }
        if (movieFilterDto.getYear() != null && !movieFilterDto.getYear().isEmpty()) {
            predicates.add(cb.equal(movie.get(Movie_.year), movieFilterDto.getYear()));
        }
        if (movieFilterDto.getCountry() != null && !movieFilterDto.getCountry().isEmpty()) {
            predicates.add(cb.equal(movie.get(Movie_.country), movieFilterDto.getCountry()));
        }
        if (movieFilterDto.getGenre() != null && !movieFilterDto.getGenre().isEmpty()) {
            predicates.add(cb.equal(movie.get(Movie_.genre), Genre.valueOf(movieFilterDto.getGenre())));
        }

        criteria.select(movie).where(
                predicates.toArray(new javax.persistence.criteria.Predicate[0])
        );

        return session.createQuery(criteria)
                .setHint(HINT_NAME, session.getEntityGraph("WithReviews"))
                .list();
    }
}
