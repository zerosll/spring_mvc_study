package com.cafe24.springmvcstudy.common.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String name;
    private String pass;
    private Host host = new Host();

    @Getter
    @Setter
    @ToString
    public static class Host {
        private String ip;
        private int port;
    }
}
