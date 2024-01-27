package com.itheima.springbootwebquickstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//请求处理类
@RestController
public class HelloController {
    // 请求处理
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World !");
        return "Hello World ~";
    }
}