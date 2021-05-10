package com.example.xxhleetcode.common.constant;

/**
 * @Author: elyuan
 * @Date: 2021/5/8 2:06 下午
 */
public enum EnumDemoo {
    MAN("Male");
    private String lable;
    private EnumDemoo(String lable){
        this.lable =lable;
    }

    public static void main(String[] args) {
        System.out.println(EnumDemoo.MAN.lable.toString());


    }

}
