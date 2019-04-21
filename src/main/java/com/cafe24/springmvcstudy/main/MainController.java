package com.cafe24.springmvcstudy.main;

import com.cafe24.springmvcstudy.common.annotation.ProgressTime;
import com.cafe24.springmvcstudy.regist.RegistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {

    private final RegistService registService;

    @RequestMapping("/")
    @ProgressTime
    public String home(Model model) {
        model.addAttribute("count", registService.getRegistVoMap().size());
        log.info("test2");
        return "main";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Noe test(@RequestBody String pBody, Model model) {
        try {
            log.debug(URLDecoder.decode(pBody, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Noe();
    }

    class Noe {

        private LocalDateTime localTime;
        private OffsetDateTime offsetTime;
        private Date date;

        public Noe() {
            localTime = LocalDateTime.now();
            offsetTime = OffsetDateTime.now();
            date = new Date();
        }

        public LocalDateTime getLocalTime() {
            return localTime;
        }

        public OffsetDateTime getOffsetTime() {
            return offsetTime;
        }

        public Date getDate() {
            return date;
        }
    }
}
