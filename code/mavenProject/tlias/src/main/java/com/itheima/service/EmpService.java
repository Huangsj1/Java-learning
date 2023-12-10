package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    public PageBean page(Integer page, Integer pageSize);

    public PageBean pagePlugin(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    public void delete(List<Integer> ids);

    public void insert(Emp emp);

    public Emp getById(Integer id);

    public void update(Emp emp);

    public Emp login(Emp emp);
}
