package com.tisv2000.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    // почему /login ставим тут, а не в RequestMapping?
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

}
