package com.example.xxhleetcode.redis;

/**
 * @Author: elyuan
 * @Date: 2021/5/6 10:56 上午
 * 1、简单动态字符串
 * 2、常数复杂度获取字符串长度。
 * 3、杜绝缓冲区溢出。
 * 4、减少修改字符串长度时所需的内存重分配次数。
 * 5、二进制安全。
 * 6、兼容部分 C 字符串函数。
 *
 */

public class Sdshdr {
    /**
     * 1、记录buf 数组中已经使用的字节数量
     * 2、等于sds所保存的字符串的长度
     */
    private int len;

    /**
     * 记录buf数组中未使用的字节数量
     */
    private int free;

    /**
     * 字节数组，用于保存字符串
     */
    char[] buf;


}
