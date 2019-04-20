package com.cafe24.springmvcstudy.common.events.listener;

import com.cafe24.springmvcstudy.common.events.CommonSpringEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnnotationDrivenContextStartedListener {

    // @Async
    @EventListener
    public void handleContextStart(CommonSpringEvent cse) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       log.info("Handling context started event : {}", cse.getMessage());
    }
}
