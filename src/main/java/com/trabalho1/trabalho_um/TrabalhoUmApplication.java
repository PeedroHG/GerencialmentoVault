package com.trabalho1.trabalho_um;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class TrabalhoUmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrabalhoUmApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer forwardToIndex() {
        return new WebMvcConfigurer() {
            @Override
            public void addViewControllers(@SuppressWarnings("null") ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("forward:/vault.html");
            }
        };
    }
}
