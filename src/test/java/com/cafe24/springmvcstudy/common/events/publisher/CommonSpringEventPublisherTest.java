package com.cafe24.springmvcstudy.common.events.publisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonSpringEventPublisherTest {

    @Autowired
    CommonSpringEventPublisher commonSpringEventPublisher;

    @Test
    public void  doStuffAndPublishAnEventTest (){

        for (int i =0 ; i < 10 ; i++) {
            commonSpringEventPublisher.doStuffAndPublishAnEvent("Test Message["+i+"]");
        }

        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}