package com.tisv2000.database.repository;

import com.tisv2000.database.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Iterator;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select r from Review r where r.movie.id = :id")
    List<Review> findAllByMovieId(Integer id);

    Iterable
    @Query("select r from Review r where r.user.id = :id")
    List<Review> findAllByUserId(Integer id);

}
