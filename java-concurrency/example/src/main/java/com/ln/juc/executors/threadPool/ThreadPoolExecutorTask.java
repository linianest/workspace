package com.ln.juc.executors.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ThreadPoolExecutorTask
 * @Author:linianest
 * @CreateTime:2021/1/7 12:54
 * @version:1.0
 * @Description TODO: 线程池执行任务
 */
public class ThreadPoolExecutorTask {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = new ThreadPoolExecutor(10, 20,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.AbortPolicy());
        IntStream.rangeClosed(0, 20).boxed().forEach(i -> {
            executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                            System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        });
        // shutdown:线程池中的线程执行完成后，退出线程池
        executorService.shutdown();
        /**
         * 立刻关闭线程并返回未执行完成的任务集合
         */
        executorService.shutdownNow();
        // 等待线程池的并行任务执行完成后，再执行串行化的任务
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("===================over====================");
    }
}
