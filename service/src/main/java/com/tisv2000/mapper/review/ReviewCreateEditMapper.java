package com.tisv2000.mapper.review;

import com.tisv2000.database.entity.Review;
import com.tisv2000.database.repository.MovieRepository;
import com.tisv2000.database.repository.UserRepository;
import com.tisv2000.dto.review.ReviewCreateEditDto;
import com.tisv2000.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewCreateEditMapper implements Mapper<ReviewCreateEditDto, Review> {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

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
        review.setMovie(
                movieRepository
                        .findById(reviewDto.getMovieId())
                        .orElseThrow());
        review.setUser(
                userRepository
                        .findById(reviewDto.getUserId())
                        .orElseThrow());
        review.setRate(reviewDto.getRate());
        review.setText(reviewDto.getText());
    }
}
