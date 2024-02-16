package com.js.GitHub.config;


import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {
    private static final String BASE_URL = "https://api.github.com";

    @Bean
    public WebClient gitWebClient() {
        return WebClient.builder().baseUrl(BASE_URL).build();
    }
}
