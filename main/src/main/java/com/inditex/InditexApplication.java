package com.inditex;

import com.inditex.config.InditexConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(InditexConfig.class)
public class InditexApplication {

    public static void main(String[] args) {
        SpringApplication.run(InditexApplication.class, args);
    }

}
