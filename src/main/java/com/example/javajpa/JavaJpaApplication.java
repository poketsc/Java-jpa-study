package com.example.javajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavaJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaJpaApplication.class, args);
    }

}
