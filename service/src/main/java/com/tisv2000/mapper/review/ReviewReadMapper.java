package com.tisv2000.mapper.review;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.Review;
import com.tisv2000.dto.movie.MovieReadDto;
import com.tisv2000.dto.review.ReviewReadDto;
import com.tisv2000.mapper.Mapper;
import com.tisv2000.mapper.movie.MovieReadMapper;
import com.tisv2000.mapper.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ReviewReadMapper implements Mapper<Review, ReviewReadDto> {

    private final UserReadMapper userReadMapper;

    @Override
    public ReviewReadDto map(Review object) {
        return new ReviewReadDto(
                object.getId(),
                userReadMapper.map(object.getUser()),
                mapMovieToDto(object.getMovie()),
                object.getText(),
                object.getRate()
        );
    }

    private MovieReadDto mapMovieToDto(Movie object) {
        return new MovieReadDto(
                object.getId(),
                object.getTitle(),
                object.getYear(),
                object.getDescription(),
                object.getCountry(),
                object.getGenre(),
                object.getImage(),
                Collections.emptyList()
                // TODO можно так сделать?
//                object.getReviews().stream().map(reviewReadMapper::map).toList()
        );
    }
}
