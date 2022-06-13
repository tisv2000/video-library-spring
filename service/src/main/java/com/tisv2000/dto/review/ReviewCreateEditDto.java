package com.tisv2000.dto.review;

import lombok.Value;

@Value
public class ReviewCreateEditDto {
    Integer userId;
    Integer movieId;
    String text;
    Integer rate;
}
