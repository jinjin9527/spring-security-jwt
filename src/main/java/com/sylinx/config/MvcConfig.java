package com.sylinx.config;

import com.sylinx.web.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    ServiceProperties properties;

    @Bean
    public WebClient webClient() {
        WebClient client = WebClient.builder().baseUrl(properties.getDns()).build();
        return client;
    }
}
