package com.example.xxhleetcode.common.annotation;

import java.util.Arrays;

/**
 * @Author: elyuan
 * @Date: 2021/4/25 7:25 下午
 */
@FilterPath("/web/update")
@FilterPath("/web/add")
@FilterPath("/web/delete")
public class FilePathMain extends BaseFilePath {


    public static void main(String[] args) {
        Class<FilePathMain> clazz = FilePathMain.class;
        FilterPath[] annotationsByType = clazz.getDeclaredAnnotationsByType(FilterPath.class);
        FilterPath[] annotationsByType1 = clazz.getAnnotationsByType(FilterPath.class);
        if(annotationsByType != null){
            Arrays.asList(annotationsByType).forEach(System.out::println);
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if(annotationsByType1 != null){
            Arrays.asList(annotationsByType1).forEach(System.out::println);
        }
        System.out.println(clazz.getAnnotation(FilterPath.class));
    }




}
@FilterPath("/web/list")
class BaseFilePath{}