package com.example.xxhleetcode;

import com.example.xxhleetcode.mybatis.service.User;
import com.example.xxhleetcode.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class XxhLeetcodeApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        int count = userService.count();
        log.info("count:"+count);
        User user = new User();
        user.setAge(12);
        user.setEmail("310292");
        user.setName("袁二龙");
        userService.testTransactionSave(user);


    }










}
