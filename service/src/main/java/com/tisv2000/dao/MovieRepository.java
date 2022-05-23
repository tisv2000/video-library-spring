package com.tisv2000.dao;

import com.tisv2000.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findById(Integer id);

    List<Movie> findAll();

    Movie save(Movie entity);

    Movie saveAndFlush(Movie entity);

    void deleteById(Integer id);


//    List<Movie> findAllByFilterQueryDsl(MovieFilterDto movieFilterDto) {
//
//        // TODO: Вынести логику создания Predicates в отдельный класс
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (movieFilterDto.getTitle() != null && !movieFilterDto.getTitle().isEmpty()) {
//            predicates.add(QMovie.movie.title.eq(movieFilterDto.getTitle()));
//        }
//        if (movieFilterDto.getYear() != null && !movieFilterDto.getYear().isEmpty()) {
//            predicates.add(QMovie.movie.year.eq(Integer.valueOf(movieFilterDto.getYear())));
//        }
//        if (movieFilterDto.getCountry() != null && !movieFilterDto.getCountry().isEmpty()) {
//            predicates.add(QMovie.movie.country.eq(movieFilterDto.getCountry()));
//        }
//        if (movieFilterDto.getGenre() != null && !movieFilterDto.getGenre().isEmpty()) {
//            predicates.add(QMovie.movie.genre.eq(Genre.valueOf(movieFilterDto.getGenre())));
//        }
//
//        return new JPAQuery<Movie>(entityManager)
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
