package com.tisv2000.dao;

import com.tisv2000.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class UserRepository extends RepositoryBase<Integer, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
