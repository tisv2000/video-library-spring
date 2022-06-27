package com.tisv2000.mapper.review;

import com.tisv2000.database.entity.Review;
import com.tisv2000.dto.review.ReviewReadDto;
import com.tisv2000.mapper.Mapper;
import com.tisv2000.mapper.movie.MovieReadMapper;
import com.tisv2000.mapper.user.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewReadMapper implements Mapper<Review, ReviewReadDto> {

//    private final MovieReadMapper movieReadMapper;
    private final UserReadMapper userReadMapper;

    @Override
    public ReviewReadDto map(Review object) {
        return new ReviewReadDto(
                object.getId(),
                userReadMapper.map(object.getUser()),
//                movieReadMapper.map(object.getMovie()),
                object.getText(),
                object.getRate()
        );
    }
}
