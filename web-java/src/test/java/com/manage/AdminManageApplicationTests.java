package com.manage;

import com.manage.sys.entity.User;
import com.manage.sys.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class AdminManageApplicationTests {

    @Resource
    private UserMapper userMapper;
//    @Test
//    void contextLoads() {
//
//    }
    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
}
