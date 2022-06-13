package com.tisv2000.database.repository;

import com.tisv2000.database.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;

@Component
public interface MovieRepository extends JpaRepository<Movie, Integer>, QuerydslPredicateExecutor<Movie> {

}
