package com.cafe24.springmvcstudy.welcome.controller;

import com.cafe24.springmvcstudy.regist.vo.MemberVo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/welcome")
@Controller
public class WelcomeController {

    private Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false) String name) {

        model.addAttribute("greeting", "안녕하세요, " + name);
        return ("welcome/hello");
    }

    /*
     produces = "text/plain"
     produces = {"text/plain", "application/*"}
     produces = MediaType.APPLICATION_JSON_UTF8_VALUE
     produces = MediaType.APPLICATION_XML_VALUE
     */
    @RequestMapping(value = "/produce", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody MemberVo produceTest() {
        return getMemberVo();
    }

    /*
     consumes = "text/plain"
     consumes = {"text/plain", "application/*"}
     consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
     consumes = MediaType.APPLICATION_XML_VALUE
     */
    @RequestMapping(value = "/consume", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody MemberVo consumeTest(String jsonObj) {

        Gson gson = new Gson();
        MemberVo memberVo2 = gson.fromJson(jsonObj, MemberVo.class);
        log.debug("memberVo2 : {}", memberVo2);
        return memberVo2;
    }

    private MemberVo getMemberVo() {
        MemberVo memberVo = new MemberVo();
        memberVo.setEmail("ehchoi@cafe24corp.com");
        memberVo.setId(1l);
        memberVo.setName("은혁");
        memberVo.setPassword("1qaz2wsx");
        memberVo.setRegisterDateTime(LocalDateTime.now());

        return memberVo;
    }
}
