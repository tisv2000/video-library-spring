package com.tisv2000.dao;

import com.tisv2000.entity.User;

import javax.persistence.EntityManager;

public class MoviePersonRepository extends RepositoryBase<Integer, User> {

    public MoviePersonRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
