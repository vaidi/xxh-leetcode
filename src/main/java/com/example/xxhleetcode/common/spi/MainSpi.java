package com.example.xxhleetcode.common.spi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author: elyuan
 * @Date: 2021/4/27 10:38 上午
 */
public class MainSpi {

    public static void main(String[] args) {
        ServiceLoader<PayService> load = ServiceLoader.load(PayService.class);
        Iterator<PayService> iterator = load.iterator();
        while (iterator.hasNext()){
            PayService payService = iterator.next();

            payService.pay();
        }
        System.out.println(3>>1);

    }

}
