package com.ln.juc.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ExecutorServiceExample5
 * @Author:linianest
 * @CreateTime:2021/1/9 16:19
 * @version:1.0
 * @Description TODO:ExecutorService API使用详解 操作线程任务队列
 */
public class ExecutorServiceExample5 {
    public static void main(String[] args) {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.execute(() -> System.out.println("i will be process because of tiggered to executed."));
        executorService.getQueue().add(() -> System.out.println(" i am added directly into the queue."));
    }
}
