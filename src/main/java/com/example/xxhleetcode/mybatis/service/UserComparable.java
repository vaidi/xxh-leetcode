package com.example.xxhleetcode.mybatis.service;

import lombok.Data;

/**
 * @Author: elyuan
 * @Date: 2021/4/28 3:25 下午
 */
@Data
public class UserComparable implements Comparable<UserComparable>{


    private String name;
    private int comparableInt;

    @Override
    public int compareTo(UserComparable o) {
        return o.comparableInt-this.comparableInt;
    }

    public static void main(String[] args) {

        Object userComparable = new UserComparable();
        System.out.println(userComparable instanceof UserComparable);




    }


}
