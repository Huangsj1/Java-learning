package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// springboot整合的单元测试的注解
@SpringBootTest
class SpringbootMybatisQuickstartApplicationTests {
    // 自动创建userMapper的bean对象
    @Autowired
    private UserMapper userMapper;

    // 1.查询测试程序
    @Test
    public void testListUser() {
//        List<User> userlist = userMapper.list();
//        List<User> userlist = userMapper.list2("M", (short) 2);
//        List<Integer> ids = Arrays.asList(1, 2, 3);
        List<User> userlist = userMapper.list4();
        userlist.stream().forEach(user -> {
            System.out.println(user);
        });
    }

    // 2.删除测试程序
    @Test
    public void testDeleteUser() {
        int delete = userMapper.delete(2);
        System.out.println(delete);
    }

    // 3.新增测试程序
    @Test
    public void testInsertUser() {
        // 一般情况下都是不知道主键id值
        User user = new User(null, "John", "重", 40, (short) 1);
        userMapper.insert(user);
        System.out.println(user.getId());
    }

    // 4.更新测试程序
    @Test
    public void testUpdateUser() {
        User user = new User(3, null, "哈哈", 30, (short) 1);
        userMapper.update(user);
    }
}
