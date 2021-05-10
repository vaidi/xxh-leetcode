//package com.example.xxhleetcode.mybatis.config;
//
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.TransactionManagementConfigurer;
//
///**
// * @Author: elyuan
// * @Date: 2021/4/27 7:43 下午
// */
//@Configuration
//@EnableConfigurationProperties
//public class MyBatisConfig implements TransactionManagementConfigurer {
//    @Bean
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return new DataSourceTransactionManager();
//    }
//}
