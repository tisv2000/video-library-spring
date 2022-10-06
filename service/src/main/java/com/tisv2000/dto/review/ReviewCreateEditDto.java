package com.tisv2000.dto.review;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class ReviewCreateEditDto {
    Integer userId;
    Integer movieId;

    @NotBlank(message = "Text must not be empty")
    String text;

    @NotNull(message = "Rate must not be empty")
    Integer rate;
}
