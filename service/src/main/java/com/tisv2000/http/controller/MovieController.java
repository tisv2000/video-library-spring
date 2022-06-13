package com.tisv2000.http.controller;

import com.tisv2000.dto.movie.MovieCreateEditDto;
import com.tisv2000.dto.movie.MovieFilterDto;
import com.tisv2000.service.MovieService;
import com.tisv2000.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @GetMapping
    public String findAllByFilter(Model model, @ModelAttribute("movieFilterDto") MovieFilterDto movieFilterDto) {
        model.addAttribute("movies", movieService.findAllByFilter(movieFilterDto));
        return "movie/movies";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        return movieService.findById(id)
                .map(movie -> {
                    model.addAttribute("movie", movie);
                    model.addAttribute("reviews", reviewService.findAllByMovieId(movie.getId()));
                    return "movie/movie";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(MovieCreateEditDto movie) {
        return "redirect:/users/" + movieService.create(movie);
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute MovieCreateEditDto movie) {
        return movieService.update(id, movie)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!movieService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/movies";
    }
}
