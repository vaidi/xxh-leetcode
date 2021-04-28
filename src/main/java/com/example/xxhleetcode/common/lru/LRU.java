package com.example.xxhleetcode.common.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2021/4/27 8:50 下午
 */
public class LRU<K,V> extends LinkedHashMap{

    private int capacity;
    LRU(int capacity){
        super(16,0.75f,true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        System.out.println(eldest.getKey()+"="+eldest.getValue());
        return size()>capacity;
    }

    public static void main(String[] args) {

        //指定缓存最大容量为4
        Map<Integer,Integer> map=new LRU<>(4);
        map.put(9,3);
        map.put(7,4);
        map.put(5,9);
        map.put(3,4);
        map.put(6,6);
        //总共put了5个元素，超过了指定的缓存最大容量
        //遍历结果
        for(Iterator<Map.Entry<Integer,Integer>> it = map.entrySet().iterator(); it.hasNext();){
            System.out.println(it.next().getKey());
        }

    }




}
