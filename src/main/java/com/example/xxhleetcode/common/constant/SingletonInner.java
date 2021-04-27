package com.example.xxhleetcode.common.constant;

/**
 * @Author: elyuan
 * @Date: 2021/4/25 9:18 下午
 */
public class SingletonInner {
    private static class Holder{
        private static SingletonInner singleton = new SingletonInner();
    }

    private SingletonInner(){}

    public static SingletonInner getInstance(){
        return Holder.singleton;
    }




}
