package com.github.zhangguqing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.github.service"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}