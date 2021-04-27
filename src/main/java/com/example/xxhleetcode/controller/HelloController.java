package com.example.xxhleetcode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author: elyuan
 * @Date: 2021/4/21 3:55 下午
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello(){
        return UUID.randomUUID().toString();
    }

}
