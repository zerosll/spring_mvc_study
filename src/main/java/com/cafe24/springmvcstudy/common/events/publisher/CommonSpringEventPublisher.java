package com.cafe24.springmvcstudy.common.events.publisher;

import com.cafe24.springmvcstudy.common.events.CommonSpringEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommonSpringEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void doStuffAndPublishAnEvent(final String message) {

        log.info("Publishing common event :  {}", message);
        CommonSpringEvent commonSpringEvent = new CommonSpringEvent(this, message);
        applicationEventPublisher.publishEvent(commonSpringEvent);
    }
}
