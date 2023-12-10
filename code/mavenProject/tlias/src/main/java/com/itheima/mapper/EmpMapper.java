package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select count(*) from emp")
    long total();

    @Select("select * from emp limit #{start}, #{pageSize}")
    List<Emp> page(Integer start, Integer pageSize);

    // 用xml来实现
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    // xml实现
    void delete(List<Integer> ids);

    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    // xml实现
    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUserNameAndPassword(Emp emp);

    // 根据部门id删除员工
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
