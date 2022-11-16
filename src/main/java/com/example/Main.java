package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
    }

}
