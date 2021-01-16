package com.ln.juc.blocking;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.blocking
 * @Name:LinkedTransferQueueExample
 * @Author:linianest
 * @CreateTime:2021/1/13 9:27
 * @version:1.0
 * @Description TODO:
 */
public class LinkedTransferQueueExample {
    public <T> LinkedTransferQueue<T> create() {
        return new LinkedTransferQueue<>();
    }
}
