package com.tisv2000.database.repository;

import com.tisv2000.database.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

//    Optional<Review> findById(Integer id);

//    List<Review> findAll();

//    Review save(Review entity);

//    Review saveAndFlush(Review entity);

//    void deleteById(Integer id);

}