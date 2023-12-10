package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        long total = empMapper.total();
        int start = (page - 1) * pageSize;
        List<Emp> list = empMapper.page(start, pageSize);
        return new PageBean(total, list);
    }

    @Override
    public PageBean pagePlugin(Integer page, Integer pageSize, String name,
                               Short gender, LocalDate begin, LocalDate end) {
        // 1.设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> pageList = (Page<Emp>) empList;
        // 3.封装PageBean对象
        return new PageBean(pageList.getTotal(), pageList.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        // 补充其余字段信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUserNameAndPassword(emp);
    }
}
