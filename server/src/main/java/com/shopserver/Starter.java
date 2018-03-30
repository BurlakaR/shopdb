package com.shopserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.shopserver")
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);}
}