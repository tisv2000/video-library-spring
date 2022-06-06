package com.tisv2000.mapper.review;

import com.tisv2000.database.entity.Review;
import com.tisv2000.dto.review.ReviewReadDto;
import com.tisv2000.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ReviewReadMapper implements Mapper<Review, ReviewReadDto> {

    @Override
    public ReviewReadDto map(Review object) {
        return new ReviewReadDto(
                object.getId(),
                object.getUser(),
                object.getMovie(),
                object.getText(),
                object.getRate()
        );
    }
}
