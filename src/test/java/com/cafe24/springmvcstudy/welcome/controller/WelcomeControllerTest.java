package com.cafe24.springmvcstudy.welcome.controller;

import com.cafe24.springmvcstudy.regist.vo.MemberVo;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String json;

    @Before
    public void setup() {
        MemberVo memberVo = new MemberVo();
        memberVo.setEmail("ehchoi@cafe24corp.com");
        memberVo.setId(1l);
        memberVo.setName("은혁");
        memberVo.setPassword("1qaz2wsx");
        memberVo.setRegisterDateTime(LocalDateTime.now());

        Gson gson = new Gson();
        json = gson.toJson(memberVo);
    }

    @Test
    public void produceTest() throws Exception {

        mockMvc.perform(get("/welcome/produce"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void consumeTest() throws Exception {

        mockMvc.perform(post("/welcome/consume")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }
}