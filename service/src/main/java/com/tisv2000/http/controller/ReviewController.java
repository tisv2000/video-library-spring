package com.tisv2000.http.controller;

import com.tisv2000.dto.review.ReviewCreateEditDto;
import com.tisv2000.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public String create(@ModelAttribute @Validated ReviewCreateEditDto reviewCreateEditDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("review", reviewCreateEditDto);
            redirectAttributes.addFlashAttribute("reviewErrors", bindingResult.getAllErrors());
            return "redirect:/movies/" + reviewCreateEditDto.getMovieId();
        }
        return "redirect:/movies/" + reviewService.create(reviewCreateEditDto).getMovie().getId();
    }

}
