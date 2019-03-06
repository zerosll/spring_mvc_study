package com.cafe24.springmvcstudy.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
