package com.example.xxhleetcode.pool;

import com.example.xxhleetcode.bean.XxhCode;
import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.stream.LongStream;

import static java.lang.Thread.sleep;

/**
 * @Author: elyuan
 * @Date: 2021/4/23 3:30 下午
 */
public class ObjectPoolMain {

    public static void main(String[] args) throws Exception {

        XxhKeyedPooledObjectFactory keyedPooledObjectFactory = new XxhKeyedPooledObjectFactory();
        KeyedObjectPool<String,XxhCode> keyedObjectPool = new GenericKeyedObjectPool<>(keyedPooledObjectFactory);


















        XxhPooledObjectFactory factory = new XxhPooledObjectFactory();
        ObjectPool<XxhCode> objectPool = new GenericObjectPool(factory);
        objectPool.addObjects(8);
        XxhCode xxhCode = objectPool.borrowObject();
        objectPool.invalidateObject(xxhCode);
        System.out.println("numActive:"+objectPool.getNumActive()+">>> numIdle:"+objectPool.getNumIdle());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(80);
        config.setMinIdle(1);

        LongStream.range(1,1000).forEach(e->{
            try {
                final XxhCode code  = objectPool.borrowObject();
                int numActive = objectPool.getNumActive();
                int numIdle = objectPool.getNumIdle();
                System.out.println("code:"+code.getId()+",numActive:"+numActive+">>> numIdle:"+numIdle);
               // objectPool.returnObject(code);

                new Thread(()->{
                    try {
                        sleep(20000);
                        objectPool.returnObject(code);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }


}
