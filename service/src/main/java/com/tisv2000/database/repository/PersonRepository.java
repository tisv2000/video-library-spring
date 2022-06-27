package com.tisv2000.database.repository;

import com.tisv2000.database.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PersonRepository extends JpaRepository<Person, Integer>, QuerydslPredicateExecutor<Person> {

}
