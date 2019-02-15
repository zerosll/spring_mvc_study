package com.cafe24.springmvcstudy.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
public class MemberController {
    @GetMapping("/login")
    public String form() {
        return "";
    }

    @PostMapping("/login")
    public String login(){
        return "";
    }
}
