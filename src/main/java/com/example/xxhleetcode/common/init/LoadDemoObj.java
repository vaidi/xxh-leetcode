package com.example.xxhleetcode.common.init;

/**
 * @Author: elyuan
 * @Date: 2021/4/26 5:56 下午
 */
public class LoadDemoObj {

    public LoadDemoObj() {
        System.err.println("loadDemoObj load:"+Thread.currentThread().getContextClassLoader());

    }

    @Override
    public String toString(){
        return "I am DemoObj";
    }

}
