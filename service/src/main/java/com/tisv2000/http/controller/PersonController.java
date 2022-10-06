package com.tisv2000.http.controller;

import com.tisv2000.dto.person.PersonCreateEditDto;
import com.tisv2000.dto.person.PersonFilterDto;
import com.tisv2000.service.MoviePersonService;
import com.tisv2000.service.MovieService;
import com.tisv2000.service.PersonService;
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
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final MovieService movieService;
    private final MoviePersonService moviePersonService;

    @GetMapping
    public String findAllByFilter(Model model, @ModelAttribute("personFilterDto") PersonFilterDto personFilterDto) {
        model.addAttribute("persons", personService.findAllByFilter(personFilterDto));
        return "person/persons";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        return personService.findById(id)
                .map(person -> {
                    model.addAttribute("person", person);
                    model.addAttribute("user", userDetails);
                    model.addAttribute("personMovies", moviePersonService.findMoviePersonsByPersonId(id));
                    return "person/person";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated PersonCreateEditDto personDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("person", personDto);
//            redirectAttributes.addFlashAttribute("personErrors", bindingResult.getAllErrors());
//            return "redirect:/movies/" + personDto.getMovieId();
//        }
        return "redirect:/persons/" + personService.create(personDto).getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        if (!personService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/persons";
    }
}
