package com.tisv2000.dao;

import com.tisv2000.entity.Review;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ReviewRepository extends RepositoryBase<Integer, Review> {

    public ReviewRepository(EntityManager entityManager) {
        super(Review.class, entityManager);
    }
}
