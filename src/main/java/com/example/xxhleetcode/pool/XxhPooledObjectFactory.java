package com.example.xxhleetcode.pool;

import com.example.xxhleetcode.bean.XxhCode;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: elyuan
 * @Date: 2021/4/23 2:51 下午
 */
@Log4j2
public class  XxhPooledObjectFactory implements PooledObjectFactory<XxhCode> {

    private AtomicInteger atomicInteger = new AtomicInteger();


    @Override
    public PooledObject<XxhCode> makeObject() throws Exception {
        XxhCode code = new XxhCode();
        code.setId(atomicInteger.incrementAndGet());
        PooledObject<XxhCode> pooledObject = new DefaultPooledObject<XxhCode>(code);
        log.info("makeObject xxh:{}",code);
        return  pooledObject;
    }

    @Override
    public void destroyObject(PooledObject<XxhCode> p) throws Exception {
        log.info("destroyObject p:{}",p);

    }

    @Override
    public boolean validateObject(PooledObject<XxhCode> p) {
        return false;
    }

    @Override
    public void activateObject(PooledObject<XxhCode> p) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<XxhCode> p) throws Exception {

    }
}
