package com.sparta.projectmovie1.movienightplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MovieNightPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieNightPlannerApplication.class, args);
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public WebClient getWebClient(){
        return WebClient.create();
    }


}
