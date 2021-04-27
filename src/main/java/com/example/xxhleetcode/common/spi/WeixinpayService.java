package com.example.xxhleetcode.common.spi;

/**
 * @Author: elyuan
 * @Date: 2021/4/27 10:33 上午
 */
public class WeixinpayService implements PayService {


    @Override
    public void pay() {
        System.out.println("Weixin pay");
    }
}
