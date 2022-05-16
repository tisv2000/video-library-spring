package com.tisv2000.dao;

import com.tisv2000.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MoviePersonRepository extends RepositoryBase<Integer, User> {

    public MoviePersonRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
