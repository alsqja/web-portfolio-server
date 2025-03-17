package com.example.webportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebPortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebPortfolioApplication.class, args);
    }

}
