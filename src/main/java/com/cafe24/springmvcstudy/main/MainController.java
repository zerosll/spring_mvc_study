package com.cafe24.springmvcstudy.main;

import com.cafe24.springmvcstudy.regist.RegistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final RegistService registService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("count",registService.getRegistVoMap().size());
        log.info("test2");
        return "main";
    }
}
