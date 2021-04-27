package com.example.xxhleetcode.common.spi;

/**
 * @Author: elyuan
 * @Date: 2021/4/27 10:31 上午
 */
public class AlipayService implements PayService {


    @Override
    public void pay() {
        System.out.println("Alipay");
    }
}
