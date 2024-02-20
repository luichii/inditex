package com.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.inditex.repository", "com.inditex.openapi.api", "com.inditex.controller",
    "com.inditex.usecase", "com.inditex.port", "com.inditex.adapter", "com.inditex.mapper"})
public class InditexApplication {

    public static void main(String[] args) {
        SpringApplication.run(InditexApplication.class, args);
    }

}
