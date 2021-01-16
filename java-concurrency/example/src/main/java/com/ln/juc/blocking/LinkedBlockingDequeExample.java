package com.ln.juc.blocking;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.collections
 * @Name:ArrayBlockingQueueExample
 * @Author:linianest
 * @CreateTime:2021/1/12 14:05
 * @version:1.0
 * @Description TODO: 固定大小空间的链表队列，当放满数据后，会让队列阻塞住
 */
public class LinkedBlockingDequeExample {



    /**
     * 1、FIFO
     * 2、数据一旦被创建，不能被修改
     * 3、
     *
     * @param size
     * @param <T>
     * @return
     */
    public <T> LinkedBlockingDeque<T> create(int size) {
        return new LinkedBlockingDeque<>(size);
    }
}
