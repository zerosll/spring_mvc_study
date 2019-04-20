package com.cafe24.springmvcstudy.common.events.listener;

import com.cafe24.springmvcstudy.common.events.CommonSpringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
//@Async
@Component
public class CommonSpringEventListener implements ApplicationListener<CommonSpringEvent> {

    @Override
    public void onApplicationEvent(CommonSpringEvent event) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Received spring custom event - {}", event.getMessage());
    }
}

