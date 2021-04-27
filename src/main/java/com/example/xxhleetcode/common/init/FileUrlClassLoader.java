package com.example.xxhleetcode.common.init;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.*;

/**
 * @Author: elyuan
 * @Date: 2021/4/26 5:59 下午
 */
@Slf4j
public class FileUrlClassLoader extends URLClassLoader {


    public FileUrlClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public FileUrlClassLoader(URL[] urls) {
        super(urls);
    }

    public FileUrlClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }


    public static void main(String[] args) throws Exception {
        String rootDir = "/Users/zejian/Downloads/Java8_Action/src/main/java/";
        File file = new File(rootDir);
        URI uri = file.toURI();
        URL[] urls = {uri.toURL()};
        log.info("URI:{},URLS:{}",uri,urls);
        FileUrlClassLoader loader = new FileUrlClassLoader(urls);
        Class<?> aClass = loader.loadClass("com.example.xxhleetcode.common.init.LoadDemoObj");
        System.out.println(aClass.newInstance().toString()+"hashCode:"+aClass.newInstance().hashCode());

        FileClassLoader fileClassLoader = new FileClassLoader(rootDir);
        Class<?> aClass1 = fileClassLoader.loadClass("com.example.xxhleetcode.common.init.LoadDemoObj");
        System.out.println(aClass1.newInstance().hashCode());


    }

}
