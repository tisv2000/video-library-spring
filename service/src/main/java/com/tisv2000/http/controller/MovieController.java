package com.tisv2000.http.controller;

import com.tisv2000.database.entity.Genre;
import com.tisv2000.database.entity.PersonRole;
import com.tisv2000.dto.movie.MovieCreateEditDto;
import com.tisv2000.dto.movie.MovieFilterDto;
import com.tisv2000.dto.movieperson.MoviePersonCreateEditDto;
import com.tisv2000.dto.review.ReviewCreateEditDto;
import com.tisv2000.service.MoviePersonService;
import com.tisv2000.service.MovieService;
import com.tisv2000.service.PersonService;
import com.tisv2000.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;
    private final MoviePersonService moviePersonService;
    private final PersonService personService;

    @GetMapping
    public String findAllByFilter(Model model,
                                  @ModelAttribute("movieFilterDto") MovieFilterDto movieFilterDto,
                                  @ModelAttribute("newMovie") MovieCreateEditDto movieCreateEditDto) {
        model.addAttribute("movieFilter", movieFilterDto);
        model.addAttribute("movies", movieService.findAllByFilter(movieFilterDto));
        model.addAttribute("newMovie", movieCreateEditDto);
        model.addAttribute("genres", Genre.values());
        return "movie/movies";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id,
                           Model model,
                           @AuthenticationPrincipal UserDetails userDetails,
                           @ModelAttribute ReviewCreateEditDto reviewCreateEditDto,
                           @ModelAttribute("moviePerson") MoviePersonCreateEditDto moviePersonDto) {
        return movieService.findById(id)
                .map(movie -> {
                    model.addAttribute("movie", movie);
                    model.addAttribute("reviews", reviewService.findAllByMovieId(movie.getId()));
                    model.addAttribute("review", reviewCreateEditDto);
                    model.addAttribute("moviePersons", moviePersonService.findMoviePersonsByMovieId(id));
                    model.addAttribute("moviePerson", moviePersonDto);
                    model.addAttribute("user", userDetails);
                    model.addAttribute("persons", personService.findAll());
                    model.addAttribute("personRoles", PersonRole.values());
                    return "movie/movie";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated MovieCreateEditDto movieDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newMovie", movieDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/movies/";
        }
        return "redirect:/movies/" + movieService.create(movieDto).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute MovieCreateEditDto movie) {
        return movieService.update(id, movie)
                .map(it -> "redirect:/users/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}")
    public String addMoviePerson(@PathVariable("id") Integer id,
                                 @ModelAttribute @Validated MoviePersonCreateEditDto moviePersonDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("moviePerson", moviePersonDto);
            redirectAttributes.addFlashAttribute("moviePersonsErrors", bindingResult.getAllErrors());
            return "redirect:/movies/" + id;
        }
        redirectAttributes.addFlashAttribute("createdMoviePerson", moviePersonService.create(moviePersonDto));
        return "redirect:/movies/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!movieService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/movies";
    }
}
