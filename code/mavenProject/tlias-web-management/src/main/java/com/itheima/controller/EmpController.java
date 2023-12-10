package com.itheima.controller;

import com.itheima.annotation.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// 员工的Controller
@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /*查询*/
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询: {}, {}", page, pageSize);
        // PageBean pageBean = empService.page(page, pageSize);
        PageBean pageBean = empService.pagePlugin(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /*批量删除*/
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除: {}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*新增员工*/
    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp);
        empService.insert(emp);
        return Result.success();
    }

    /*根据id查询员工*/
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id:{}查询员工", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /*修改员工数据*/
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("根据id更新员工信息:{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
