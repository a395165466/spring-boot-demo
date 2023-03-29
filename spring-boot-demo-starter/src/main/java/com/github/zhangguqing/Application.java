package com.github.zhangguqing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootConfiguration
@SpringBootApplication(scanBasePackages = {"com.github.service", "com.github.zhangguoqing"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}