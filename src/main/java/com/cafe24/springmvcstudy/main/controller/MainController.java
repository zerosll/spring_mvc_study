package com.cafe24.springmvcstudy.main.controller;

import com.cafe24.springmvcstudy.regist.service.RegistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final RegistService registService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("count",registService.getRegistVoMap().size());

        return "main";
    }
}
