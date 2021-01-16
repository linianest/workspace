package com.ln.juc.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.collections
 * @Name:ArrayBlockingQueueExample
 * @Author:linianest
 * @CreateTime:2021/1/12 14:05
 * @version:1.0
 * @Description TODO: 固定大小空间的数组队列，当放满数据后，会让队列阻塞住
 */
public class ArrayBlockingQueueExample {


    /**
     * 1、FIFO
     * 2、数据一旦被创建，不能被修改
     * 3、
     *
     * @param size
     * @param <T>
     * @return
     */
    public <T> ArrayBlockingQueue<T> create(int size) {
        return new ArrayBlockingQueue<>(size);
    }
}
