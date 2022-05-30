package com.tisv2000.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.QMovie;
import com.tisv2000.database.querydsl.QPredicates;
import com.tisv2000.dto.MovieFilterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MovieRepository extends JpaRepository<Movie, Integer> {

//    List<Movie> findAllByFilterQueryDsl(MovieFilterDto movieFilterDto) {
//
//        QPredicates.builder()
//                .add(movieFilterDto.getTitle(), QMovie.movie.title::containsIgnoreCase)
//                .add(movieFilterDto.getYear(), QMovie.movie.year::eq)
//                .add(movieFilterDto.getCountry(), QMovie.movie.country::containsIgnoreCase)
//                .add(movieFilterDto.getGenre(), QMovie.movie.genre.stringValue()::containsIgnoreCase)
//                .build();
//
//
//        return new JPAQuery<Movie>()
//                .select(QMovie.movie)
//                .from(QMovie.movie)
//                .where(predicates.toArray(Predicate[]::new))
//                .setHint(HINT_NAME, entityManager.getEntityGraph("WithReviews"))
//                .fetch();
//    }

//    List<Movie> findAllByFilterCriteriaApi(MovieFilterDto movieFilterDto) {
//        var cb = entityManager.getCriteriaBuilder();
//
//        var criteria = cb.createQuery(Movie.class);
//
//        var movie = criteria.from(Movie.class);
//
//        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
//
//        if (movieFilterDto.getTitle() != null && !movieFilterDto.getTitle().isEmpty()) {
//            predicates.add(cb.equal(movie.get(Movie_.title), movieFilterDto.getTitle()));
//        }
//        if (movieFilterDto.getYear() != null && !movieFilterDto.getYear().isEmpty()) {
//            predicates.add(cb.equal(movie.get(Movie_.year), movieFilterDto.getYear()));
//        }
//        if (movieFilterDto.getCountry() != null && !movieFilterDto.getCountry().isEmpty()) {
//            predicates.add(cb.equal(movie.get(Movie_.country), movieFilterDto.getCountry()));
//        }
//        if (movieFilterDto.getGenre() != null && !movieFilterDto.getGenre().isEmpty()) {
//            predicates.add(cb.equal(movie.get(Movie_.genre), Genre.valueOf(movieFilterDto.getGenre())));
//        }
//
//        criteria.select(movie).where(
//                predicates.toArray(new javax.persistence.criteria.Predicate[0])
//        );
//
//        return entityManager.createQuery(criteria)
//                .setHint(HINT_NAME, entityManager.getEntityGraph("WithReviews")).getResultList();
//    }
}
