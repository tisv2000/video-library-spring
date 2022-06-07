package com.tisv2000.mapper.review;

import com.tisv2000.database.entity.Review;
import com.tisv2000.dto.review.ReviewCreateEditDto;
import com.tisv2000.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewCreateEditMapper implements Mapper<ReviewCreateEditDto, Review> {

    @Override
    public Review map(ReviewCreateEditDto fromObject, Review toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Review map(ReviewCreateEditDto object) {
        var review = new Review();
        copy(object, review);
        return review;
    }

    private void copy(ReviewCreateEditDto reviewDto, Review review) {
        review.setMovie(reviewDto.getMovie());
        review.setUser(reviewDto.getUser());
        review.setRate(reviewDto.getRate());
        review.setText(reviewDto.getText());
    }
}
