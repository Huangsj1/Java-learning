package com.itheima.springbootwebquickstart.controller;

import com.itheima.springbootwebquickstart.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {
    // 一、简单参数
    // 1.原始方式获取请求参数
    @RequestMapping("/simpleParam1")
    public String simpleParam1(HttpServletRequest request){
        // 获得请求参数
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");

        int age = Integer.parseInt(ageStr);
        System.out.println(name + ":" + age);
        return "OK";
    }

    // 2.基于Springboot方式接收
    //  请求参数名与方法形参变量名相同，不同就需要用@RequestParam来映射
    @RequestMapping("/simpleParam2")
    public String simpleParam2(String name, @RequestParam(name = "age", required = false) Integer myage) {
        System.out.println(name + ":" + myage);
        return "OKK";
    }


    // 二、实体参数
    // 请求参数名要和实体对象属性名相同
    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "OOK";
    }


    // 三、数组集合参数
    // 1.数组参数
    // 所有参数名都需要是数组名
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "OKOK";
    }

    // 2.集合参数
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "okok";
    }


    // 四、日期参数
    // 需要指定格式,且每个单位的前面位数不足需要补零
    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "OK";
    }

    // 五、JSON参数
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);
        return "OKK";
    }

    // 六、路径参数
    @RequestMapping("/pathParam/{id}/{name}")
    public String pathParam(@PathVariable Integer id, @PathVariable String name) {
        System.out.println(id + ":" + name);
        return "OK";
    }
}
