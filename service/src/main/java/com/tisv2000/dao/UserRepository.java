package com.tisv2000.dao;

import com.tisv2000.entity.User;

import javax.persistence.EntityManager;

public class UserRepository extends RepositoryBase<Integer, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
