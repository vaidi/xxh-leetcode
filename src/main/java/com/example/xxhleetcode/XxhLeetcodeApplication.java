package com.example.xxhleetcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.xxhleetcode.mybatis.service.mapper")
@SpringBootApplication
public class XxhLeetcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(XxhLeetcodeApplication.class, args);
    }

}
