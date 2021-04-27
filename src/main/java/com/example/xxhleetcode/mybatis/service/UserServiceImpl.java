package com.example.xxhleetcode.mybatis.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xxhleetcode.mybatis.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: elyuan
 * @Date: 2021/4/27 5:04 下午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
