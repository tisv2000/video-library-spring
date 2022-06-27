package com.tisv2000.http.controller;

import com.tisv2000.dto.review.ReviewCreateEditDto;
import com.tisv2000.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public String create(ReviewCreateEditDto reviewCreateEditDto) {
        // временно пока не решу проблему с циклической зависимостью
        return "redirect:/movies/"; // + reviewService.create(reviewCreateEditDto).getMovie().getId();
    }

}
