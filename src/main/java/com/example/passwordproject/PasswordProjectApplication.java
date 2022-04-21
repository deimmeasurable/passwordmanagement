package com.example.passwordproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example.passwordproject")
@SpringBootApplication
public class PasswordProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordProjectApplication.class, args);
    }

}
