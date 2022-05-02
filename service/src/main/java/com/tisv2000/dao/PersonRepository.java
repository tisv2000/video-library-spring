package com.tisv2000.dao;

import com.tisv2000.entity.Person;

import javax.persistence.EntityManager;

public class PersonRepository extends RepositoryBase<Integer, Person> {

    public PersonRepository(EntityManager entityManager) {
        super(Person.class, entityManager);
    }
}
