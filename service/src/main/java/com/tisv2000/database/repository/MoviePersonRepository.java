package com.tisv2000.database.repository;

import com.tisv2000.database.entity.MoviePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Integer> {

    Optional<MoviePerson> findById(Integer id);

    List<MoviePerson> findAll();

//    MoviePerson save(MoviePerson entity);

    MoviePerson saveAndFlush(MoviePerson entity);

    void deleteById(Integer id);
}
