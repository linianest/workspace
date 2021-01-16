package com.ln.juc.blocking;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.blocking
 * @Name:SynchronousQueueExample
 * @Author:linianest
 * @CreateTime:2021/1/13 9:24
 * @version:1.0
 * @Description TODO:
 */
public class SynchronousQueueExample {
    public <T> SynchronousQueue<T> create() {
        return new SynchronousQueue();
    }
}
