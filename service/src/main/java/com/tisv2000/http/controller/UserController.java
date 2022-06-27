package com.tisv2000.http.controller;

import com.tisv2000.database.entity.Gender;
import com.tisv2000.database.entity.Role;
import com.tisv2000.dto.user.UserCreateEditDto;
import com.tisv2000.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll() {
        // TODO implement
        return "user/users";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("genders", Gender.values());
        return "user/registration";
    }

    @PostMapping
    public String create(@ModelAttribute UserCreateEditDto userDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        // TODO rewatch how this errors check works
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/user/registration";
        }
        userService.create(userDto);
        return "redirect:/login";
    }

}
