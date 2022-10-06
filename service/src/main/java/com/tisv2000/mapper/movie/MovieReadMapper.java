package com.tisv2000.mapper.movie;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.Review;
import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.dto.review.ReviewReadDto;
import com.tisv2000.mapper.Mapper;
import com.tisv2000.mapper.review.ReviewReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieReadMapper implements Mapper<Movie, MovieReadDto> {

    private final ReviewReadMapper reviewReadMapper;

    @Override
    public MovieReadDto map(Movie object) {
        return new MovieReadDto(
                object.getId(),
                object.getTitle(),
                object.getYear(),
                object.getDescription(),
                object.getCountry(),
                object.getGenre(),
                object.getImage(),
                getReviews(object.getReviews())
        );
    }

    private List<ReviewReadDto> getReviews(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return Collections.emptyList();
        }
        return reviews.stream().map(reviewReadMapper::map).toList();
    }
}
