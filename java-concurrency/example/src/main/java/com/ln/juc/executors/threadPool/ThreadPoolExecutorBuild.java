package com.ln.juc.executors.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ThreadPoolExecutorBuild
 * @Author:linianest
 * @CreateTime:2021/1/7 12:01
 * @version:1.0
 * @Description TODO: 线程池创建及使用
 */
public class ThreadPoolExecutorBuild {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) buildThreadPoolExecutor();
        int activeCount = -1;
        int queueSize = -1;
        while (true) {
            if (activeCount != executorService.getActiveCount() || queueSize != executorService.getQueue().size()) {
                System.out.println("ActiveCount: "+executorService.getActiveCount());
                System.out.println("CorePoolSize: "+executorService.getCorePoolSize());
                System.out.println("QueueSize: "+executorService.getQueue().size());
                System.out.println("MaximumPoolSize: "+executorService.getMaximumPoolSize());
                activeCount = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();
                System.out.println("====================================");
            }

        }
    }

    /**
     * Testing point.
     * <p>
     * 1.coreSize=1,MaxSize=2 blockingQueue is empty what happen when submit 3 task?
     * 2.coreSize=1,MaxSize=2 blockingQueue size=5 what happen when submit 7 task?
     * 3.coreSize=1,MaxSize=2 blockingQueue size=5 what happen when submit 8 task?
     * </p>
     * <p>
     * int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * RejectedExecutionHandler handler
     */
    public static ExecutorService buildThreadPoolExecutor() {

        final ExecutorService executorService = new ThreadPoolExecutor(1, 2,
                30,TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.AbortPolicy());
        System.out.println("== The ThreadPoolExecutor create done.");
        executorService.execute(() -> sleepSeconds(100));
        executorService.execute(() -> sleepSeconds(100));
        executorService.execute(() -> sleepSeconds(100));
        return executorService;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
