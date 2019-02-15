package com.cafe24.springmvcstudy.regist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/regist")
@Controller
public class RegistController {

    @RequestMapping("/step1")
    public String handleStep1() {
        return ("register/step1");
    }

    @PostMapping("/step2")
    public String handleStep2() {
        return ("register/step2");
    }
}
