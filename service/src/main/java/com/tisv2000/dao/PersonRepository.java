package com.tisv2000.dao;

import com.tisv2000.entity.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PersonRepository extends RepositoryBase<Integer, Person> {

    public PersonRepository(EntityManager entityManager) {
        super(Person.class, entityManager);
    }
}
