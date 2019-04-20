package com.cafe24.springmvcstudy.common.events;

import org.springframework.context.ApplicationEvent;


public class CommonSpringEvent extends ApplicationEvent {
    private String message;

    public CommonSpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}