package com.example.xxhleetcode;

import com.example.xxhleetcode.mybatis.service.User;
import com.example.xxhleetcode.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class XxhLeetcodeApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        List<User> list = userService.list();
        list.stream().forEach(System.out::println);
    }

}
