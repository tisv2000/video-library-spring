package com.tisv2000.dto.review;

import com.tisv2000.database.entity.Movie;
import com.tisv2000.database.entity.User;
import lombok.Value;

@Value
public class ReviewCreateEditDto {
    User user;
    Movie movie;
    String text;
    Integer rate;
}
