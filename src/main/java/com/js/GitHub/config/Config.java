package com.js.GitHub.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {
    private static final String BASE_URL = "https://api.github.com";

    @Bean
    public WebClient gitWebClient() {
        return WebClient.builder().baseUrl(BASE_URL).build();
    }
}
