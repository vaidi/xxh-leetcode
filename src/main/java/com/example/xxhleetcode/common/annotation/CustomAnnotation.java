package com.example.xxhleetcode.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: elyuan
 * @Date: 2021/4/25 5:24 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Reference{
    boolean next() default false;
}
/**
 * @author erlong
 */
public @interface CustomAnnotation {

    enum Status{FIXED,NORMAL};

    Status status() default Status.FIXED;

    boolean showSupport() default false;

    String name() default "";

    Class<?> testCase() default void.class;

    Reference reference() default @Reference(next = true);

    long[] value();
}
