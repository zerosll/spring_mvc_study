package com.cafe24.springmvcstudy.regist.controller;

import com.cafe24.springmvcstudy.regist.service.RegistService;
import com.cafe24.springmvcstudy.regist.entity.RegistVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RequiredArgsConstructor
@RequestMapping("/regist")
@Controller
public class RegistController {

    private Logger log = LoggerFactory.getLogger(RegistController.class);
    private  final RegistService registService;
    public RegistController(RegistService registService) {
        this.registService = registService;
    }

    @RequestMapping("/step1")
    public String handleStep1() {
        return ("register/step1");
    }

    @PostMapping("/step2")
    public String handleStep2(
            @RequestParam(value = "agree", defaultValue = "false") Boolean agree,
            Model model) {
        if (!agree) {
            return "register/step1";
        }
        model.addAttribute("registVo", new RegistVo());
        return "register/step2";
    }

    @GetMapping("/step2")
    public String handleStep2Get() {
        return "redirect:/regist/step1";
    }

    @PostMapping("/step3")
    public String handleStep3(RegistVo regReq) {
        try {
            System.out.println(regReq);
            registService.regist(regReq);
            return "register/step3";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "register/step2";
        }
    }
}
