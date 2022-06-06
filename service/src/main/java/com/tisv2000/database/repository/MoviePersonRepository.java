package com.tisv2000.database.repository;

import com.tisv2000.database.entity.MoviePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Integer> {

}
