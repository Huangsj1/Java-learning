package com.itheima.controller;

import com.itheima.annotation.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 部门管理的Controller
@RestController
@Slf4j              // 日志输出
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /*查询部门数据*/
    // @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping
    public Result list() {
        log.info("查询部分全部数据");

        // 调用service查询部门数据
        List<Dept> deptlist = deptService.list();

        return Result.success(deptlist);
    }

    /*删除部门数据*/
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门" + id);
        deptService.delete(id);
        return Result.success();
    }

    /*新增部门数据*/
    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept) {
        log.info("新增部门" + dept);
        deptService.insert(dept);
        return Result.success();
    }

    /*根据id查询部门*/
    @GetMapping("/{id}")
    public Result find(@PathVariable Integer id) {
        log.info("根据id查询部门" + id);
        Dept dept = deptService.find(id);
        return Result.success(dept);
    }

    /*根据id修改部门*/
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("根据id修改部门" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
