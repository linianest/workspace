package com.ln.juc.blocking;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.blocking
 * @Name:PriorityBlockingQueueExample
 * @Author:linianest
 * @CreateTime:2021/1/12 16:23
 * @version:1.0
 * @Description TODO: 无边界大小的阻塞队列
 */
public class PriorityBlockingQueueExample {
    public <T> PriorityBlockingQueue<T> create(int size) {
        return new PriorityBlockingQueue<>(size);
    }
}
