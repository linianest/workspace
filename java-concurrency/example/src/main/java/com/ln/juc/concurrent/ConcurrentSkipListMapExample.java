package com.ln.juc.concurrent;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.concurrent
 * @Name:ConcurrentSkipListMapExample
 * @Author:linianest
 * @CreateTime:2021/1/13 17:01
 * @version:1.0
 * @Description TODO: 并发集合-跳表数据结构的Map
 */
public class ConcurrentSkipListMapExample {

    public static <k,v>ConcurrentSkipListMap<k,v> create(){
        return new ConcurrentSkipListMap();
    }
}
