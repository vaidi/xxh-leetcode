package com.example.xxhleetcode.pool;

import com.example.xxhleetcode.bean.XxhCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.DestroyMode;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: elyuan
 * @Date: 2021/4/23 4:56 下午
 */
@Slf4j
public class XxhKeyedPooledObjectFactory implements KeyedPooledObjectFactory<String,XxhCode> {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public PooledObject<XxhCode> makeObject(String key) throws Exception {
        XxhCode code = new XxhCode();
        code.setId(atomicInteger.incrementAndGet());
        PooledObject<XxhCode> pooledObject = new DefaultPooledObject<XxhCode>(code);
        log.info("makeObject xxh:{}",code);
        return  pooledObject;
    }

    @Override
    public void destroyObject(String key, PooledObject<XxhCode> p) throws Exception {

    }

    @Override
    public void destroyObject(String key, PooledObject<XxhCode> p, DestroyMode mode) throws Exception {

    }

    @Override
    public boolean validateObject(String key, PooledObject<XxhCode> p) {
        return false;
    }

    @Override
    public void activateObject(String key, PooledObject<XxhCode> p) throws Exception {

    }

    @Override
    public void passivateObject(String key, PooledObject<XxhCode> p) throws Exception {

    }
}
