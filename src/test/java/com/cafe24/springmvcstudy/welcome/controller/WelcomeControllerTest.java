package com.cafe24.springmvcstudy.welcome.controller;

import com.cafe24.springmvcstudy.common.properties.MailProperties;
import com.cafe24.springmvcstudy.regist.MemberDto;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest
//@WebMvcTest 사용시 다른 설정들은 자동으로 올리지 않기 때문에 @Repository나 @Resource, @Service, @Component등은 사용할 수 없습니다.

@WebMvcTest(controllers = {WelcomeController.class})
public class WelcomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MailProperties mailProperties;

    private String json;

    @Before
    public void setup() {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("ehchoi@cafe24corp.com");
        memberDto.setName("은혁");
        memberDto.setPassword("1qaz2wsx");

        Gson gson = new Gson();
        json = gson.toJson(memberDto);
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
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE) // Controller 입장에서 consume
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE) // Controller 입장에서 produce
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                ;
    }


}