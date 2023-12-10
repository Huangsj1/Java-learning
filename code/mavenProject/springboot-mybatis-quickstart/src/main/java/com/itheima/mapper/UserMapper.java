package com.itheima.mapper;

import org.apache.ibatis.annotations.*;
import com.itheima.pojo.User;

import java.util.ArrayList;
import java.util.List;

@Mapper // 程序运行时，会自动生成该接口实现类对象，并将该对象交给IOC容器管理
public interface UserMapper {
    // 1.查询全部用户信息
    //  返回值是查询的所有元组
    @Select("select * from tb_user")
    public List<User> list();

    public List<User> list2(String username, Short gender);

    public List<User> list3(List<Integer> ids);

    public List<User> list4();

    // 2.删除用户信息
    //  返回值表示影响/删除的记录数
    @Delete("delete from tb_user where id = #{id}")
    public int delete(Integer id);

    // 3.新增用户信息
    //  在不知道主键id的情况下，可以通过@Options来将插入后得到的id赋值到变量中
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tb_user values(#{id}, #{username}, #{name}, #{age}, #{gender});")
    public void insert(User user);

    // 4.更新用户信息
    @Update("update tb_user set name=#{name}, age=#{age} where id=#{id}")
    public void update(User user);
}
