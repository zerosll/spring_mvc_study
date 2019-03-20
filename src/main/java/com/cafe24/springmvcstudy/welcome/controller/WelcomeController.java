package com.cafe24.springmvcstudy.welcome.controller;

import com.cafe24.springmvcstudy.common.properties.MailProperties;
import com.cafe24.springmvcstudy.regist.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequestMapping("/welcome")
@Controller
public class WelcomeController {

    //private Logger log = LoggerFactory.getLogger(this.getClass());
    private final MailProperties mailProperties;

    public WelcomeController(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    //@GetMapping("/hello")
    // /welcome/hello
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String hello(Model model, @RequestParam(value = "name", required = false) String name) {

        model.addAttribute("greeting", "안녕하세요, " + name);

        log.debug("메일 이름  : {}", mailProperties.getName());
        log.debug("호스트   : {} asdhjasdklj {}", mailProperties.getHost(), 1);

        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
        return ("welcome/hello");
    }

    /*
     produces = "text/plain"
     produces = {"text/plain", "application/*"}
     produces = MediaType.APPLICATION_JSON_UTF8_VALUE
     produces = MediaType.APPLICATION_XML_VALUE
     */
    @RequestMapping(value = "/produce", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody
    MemberDto produceTest() {
        return getMemberVo();
    }

    /*
     consumes = "text/plain"
     consumes = {"text/plain", "application/*"}
     consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
     consumes = MediaType.APPLICATION_XML_VALUE
     */
    @RequestMapping(value = "/consume", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    MemberDto consumeTest(MemberDto memberDto2) {

        //Gson gson = new Gson();
        //MemberDto memberDto2 = gson.fromJson(jsonObj, MemberDto.class);
        log.debug("memberDto2 : {}", memberDto2);
        return memberDto2;
    }


    @GetMapping("/redirect")
    public String helloPost(RedirectAttributes rttr, @RequestParam(value = "name", required = false) String name){
        rttr.addFlashAttribute("greeting",name);
        return "redirect:/welcome/receive";
    }
    @GetMapping("/receive")
    public String receive(Model model){
        log.debug(model.toString());
        return "welcome/hello";
    }

    private MemberDto getMemberVo() {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("ehchoi@cafe24corp.com");
        memberDto.setName("은혁");
        memberDto.setPassword("1qaz2wsx");

        return memberDto;
    }


}
