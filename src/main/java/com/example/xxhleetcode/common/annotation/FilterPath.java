package com.example.xxhleetcode.common.annotation;

import java.lang.annotation.*;



/**
 * @author erlong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
 @interface FilterPaths {
    FilterPath[] value();
}
/**
 * @Author: elyuan
 * @Date: 2021/4/25 7:19 下午
 */
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FilterPaths.class)
public @interface FilterPath {

    String value();
}
