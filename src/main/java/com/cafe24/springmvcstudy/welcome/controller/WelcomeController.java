package com.cafe24.springmvcstudy.welcome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/welcome")
@Controller
public class WelcomeController {

    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false) String name) {

        model.addAttribute("greeting", "안녕하세요, " + name);
        return ("welcome/hello");
    }

}
