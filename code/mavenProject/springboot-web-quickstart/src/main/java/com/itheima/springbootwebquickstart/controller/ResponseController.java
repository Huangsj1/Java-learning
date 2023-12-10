package com.itheima.springbootwebquickstart.controller;

import com.itheima.springbootwebquickstart.pojo.Address;
import com.itheima.springbootwebquickstart.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {
    @RequestMapping("/respHello")
    public Result hello() {
        System.out.println("Hello World");
        return Result.success("Hello World");
    }

    @RequestMapping("/respAddr")
    public Result getAddr() {
        Address addr = new Address();
        addr.setProvince("广东");
        addr.setCity("广州");
        System.out.println("get Addr");
        return Result.success(addr);
    }
}
