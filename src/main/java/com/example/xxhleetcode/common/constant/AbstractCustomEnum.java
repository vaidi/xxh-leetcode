package com.example.xxhleetcode.common.constant;

/**
 * @Author: elyuan
 * @Date: 2021/4/25 8:55 下午
 */
public enum AbstractCustomEnum {


    FIRST{
        @Override
        public String getInfo() {
            return "first day";
        }

        @Override
        public int getValue() {
            return 1;
        }
    },
    SECOND{
        @Override
        public String getInfo() {
            return "second day";
        }

        @Override
        public int getValue() {
            return 2;
        }
    };
    public abstract String getInfo();

    public abstract int getValue();

    public static void main(String[] args) {
        System.out.println("F:"+AbstractCustomEnum.FIRST.getInfo()+",value:"+AbstractCustomEnum.FIRST.getValue());
        System.out.println("F.ordinal:"+AbstractCustomEnum.FIRST.ordinal());
        System.out.println("F.name:"+AbstractCustomEnum.FIRST.name());
        System.out.println("F.toString:"+AbstractCustomEnum.FIRST.toString());



    }

}
