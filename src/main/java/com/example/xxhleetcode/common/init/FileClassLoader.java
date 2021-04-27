package com.example.xxhleetcode.common.init;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

/**
 * @Author: elyuan
 * @Date: 2021/4/26 5:11 下午
 */
@Slf4j
public class FileClassLoader extends ClassLoader {

    private String rootDir;

    public FileClassLoader(String rootDir){
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        log.info("fileClassLoader findClass， name:{}",name);
        byte[] classData = getClassData(name);
        if(classData == null){
            throw new ClassNotFoundException();
        }else{
            return defineClass(name,classData,0,classData.length);
        }
    }

    private byte[] getClassData(String name) {
        String path = rootDir+ File.separatorChar+name.replace(".",File.separator)+".class";
        try{
            InputStream inputStream = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            byte[] buffer = new byte[bufferSize];
            int bytesNumRead = 0;
            while ((bytesNumRead = inputStream.read(buffer))!= -1){
                baos.write(buffer,0,bytesNumRead);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        FileClassLoader loader1 = new FileClassLoader("erlong");

        System.out.println("自定义类加载器的父加载器: "+loader1.getParent());
        System.out.println("系统默认的AppClassLoader: "+ClassLoader.getSystemClassLoader());
        System.out.println("AppClassLoader的父类加载器: "+ClassLoader.getSystemClassLoader().getParent());
        System.out.println("ExtClassLoader的父类加载器: "+ClassLoader.getSystemClassLoader().getParent().getParent());
        System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        String rootDir="/Users/zejian/Downloads/Java8_Action/src/main/java/";
        FileClassLoader loader = new FileClassLoader(rootDir);
        try {
            Class<?> aClass = loader.loadClass("com.example.xxhleetcode.common.init.LoadDemoObj");
            System.out.println(aClass.newInstance().toString());
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            System.out.println("当前累加载器:"+contextClassLoader);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
