package com.tisv2000.database.repository;

import com.tisv2000.database.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findById(Integer id);

    List<Person> findAll();

    Person save(Person entity);

    Person saveAndFlush(Person entity);

    void deleteById(Integer id);
}
