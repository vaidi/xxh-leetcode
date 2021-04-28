package com.example.xxhleetcode.mybatis.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xxhleetcode.mybatis.service.mapper.UserMapper;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Thread.sleep;

/**
 * @Author: elyuan
 * @Date: 2021/4/27 5:04 下午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    @Override
    @Transactional(rollbackFor = Exception.class,timeout = 20,propagation = Propagation.NEVER)
    public void testTransactionSave(User user) {
        baseMapper.insert(user);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
