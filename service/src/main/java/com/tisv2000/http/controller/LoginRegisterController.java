package com.tisv2000.http.controller;

import com.tisv2000.database.entity.Gender;
import com.tisv2000.database.entity.Role;
import com.tisv2000.dto.user.UserCreateEditDto;
import com.tisv2000.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginRegisterController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model,
                                   @ModelAttribute("user") UserCreateEditDto userDto) {

        model.addAttribute("user", userDto);
        model.addAttribute("roles", Role.values());
        model.addAttribute("genders", Gender.values());
//        return "forward:user/registration";
        return "user/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute @Validated UserCreateEditDto userDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/registration";
        }
        userService.create(userDto);
        return "redirect:/login";
    }
}
