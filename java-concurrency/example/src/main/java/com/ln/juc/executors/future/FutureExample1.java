package com.ln.juc.executors.future;

import java.util.concurrent.*;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:FutureExample1
 * @Author:linianest
 * @CreateTime:2021/1/9 16:25
 * @version:1.0
 * @Description TODO:future&callable详解
 */
public class FutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testGet();
        testGetTimeOut();
    }

    private static void testGet() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //===============================
        System.out.println("==========i will be printed quickly.=================");
        //===============================
        final Thread callerThread = Thread.currentThread();
        new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // main线程打断任务执行
            callerThread.interrupt();
        }).start();
        /**
         * future.get()：会阻塞当前调用的线程，也就是main线程
         */
        final Integer result = future.get();
        System.out.println("===============" + result);

        System.out.println(executorService.getActiveCount());
        executorService.shutdown();
    }

    /**
     * todo 任务实际执行超时，但任务线程仍然在执行
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    private static void testGetTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final Future<Integer> future = executorService.submit(() -> {
            try {
                // 任务实际执行花费时长10秒
                TimeUnit.SECONDS.sleep(10);
                System.out.println("=====================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        // future.get(5, TimeUnit.SECONDS);认为任务执行花费时间是5秒内
        final Integer result = future.get(5, TimeUnit.SECONDS);
        System.out.println(result);
    }
}
