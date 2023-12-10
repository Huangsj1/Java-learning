package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    /*阿里云OSS存储*/
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名:{}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，文件访问的url为:{}", url);
        return Result.success(url);
    }


    /*本地存储*/
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        // 获取文件名
//        String originalFileName = image.getOriginalFilename();
//
//        // 构造唯一文件名
//        int index = originalFileName.indexOf(".");
//        String lastName = originalFileName.substring(index);
//        String newFileName = UUID.randomUUID().toString() + lastName;
//        log.info("新的文件名: {}", newFileName);
//
//        // 将文件存储在服务器的磁盘中 E:\计算机自学指南\java\Java-learning\code\mavenProject\data\desc
//        String path = "E:\\计算机自学指南\\java\\Java-learning\\code\\mavenProject\\data\\desc\\" + newFileName;
//        image.transferTo(new File(path));
//
//        return Result.success(path);
//    }
}
