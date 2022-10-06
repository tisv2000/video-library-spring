package com.tisv2000.database.repository;

import com.tisv2000.database.entity.MoviePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Integer> {

    @Query("select mp from MoviePerson mp inner join Movie m on mp.person.id = :id and mp.movie.id = m.id")
    List<MoviePerson> findAllByPersonId(Integer id);

    @Query("select mp from MoviePerson mp inner join Person p on mp.movie.id = :id and mp.person.id = p.id")
    List<MoviePerson> findAllByMovieId(Integer id);
}
