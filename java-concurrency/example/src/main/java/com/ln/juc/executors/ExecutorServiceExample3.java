package com.ln.juc.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ExecutorServiceExample3
 * @Author:linianest
 * @CreateTime:2021/1/9 14:16
 * @version:1.0
 * @Description TODO:ExecutorService API使用详解
 */
public class ExecutorServiceExample3 {
    public static void main(String[] args) throws InterruptedException {

        test();
    }

    /**
     * 创建了5个线程，提交一个任务，激活一个线程
     *
     * @throws InterruptedException
     */
    private static void test() throws InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        System.out.println(executorService.getActiveCount());
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(executorService.getActiveCount());

    }

    /**
     * allowCoreThreadTimeOut允许核心运行线程超时
     */
    private static void testAllowCoreThreadTimeOut() {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);
        System.out.println(executorService.getActiveCount());
        IntStream.range(0, 5).boxed().forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        );
    }

    /**
     * 超时移除执行的任务
     * @throws InterruptedException
     */
    private static void testRemove() throws InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(executorService.getActiveCount());

        Runnable r=()->{
            System.out.println("i will never be executed");
        };
        executorService.execute(r);
        TimeUnit.MILLISECONDS.sleep(20);
        executorService.remove(r);
    }

    /**
     * 创建线程池后，将核心线程启动
     * @throws InterruptedException
     */
    private static void testPrestartCoreThread() throws InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        executorService.prestartCoreThread();
        executorService.setKeepAliveTime(10, TimeUnit.SECONDS);
        executorService.allowCoreThreadTimeOut(true);
        executorService.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        TimeUnit.MILLISECONDS.sleep(20);
        System.out.println(executorService.getActiveCount());
    }



}
