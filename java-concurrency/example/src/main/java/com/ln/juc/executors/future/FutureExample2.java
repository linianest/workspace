package com.ln.juc.executors.future;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:FutureExample2
 * @Author:linianest
 * @CreateTime:2021/1/10 10:25
 * @version:1.0
 * @Description TODO:future&callable详解
 */
public class FutureExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testGet();
//        testIsDone();
//        testCanacel();
//        testCanacel2();
//        testCanacel2_1();
        testCanacel2_2();
    }

    /**
     * try to cancel maybe failed
     * <ul>
     *     <li>task is completed already.</li>
     *     <li>has already been canceled.</li>
     * </ul>
     */
    private static void testCanacel() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final AtomicBoolean running = new AtomicBoolean(true);
        final Future<Integer> future = executorService.submit(() -> {
/*            try {
                       TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName());
                throw new RuntimeException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/


            //=========================
            while (running.get()) {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.MILLISECONDS.sleep(1);

            }
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(3);
        System.out.println(future.cancel(true));
        // 第二次cancel false
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());


//        executorService.shutdown();
    }

    /**
     * todo 温和的终止任务
     * todo 任务被cancel后，仍然会执行，但是如果绑定Thread.interrupted(),任务执行体在循环
     * 内执行，就能让任务cancel
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testCanacel2() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final AtomicBoolean running = new AtomicBoolean(true);
        final Future<Integer> future = executorService.submit(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("task: "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            // todo 通过Thread.interrupted()方式，中断任务执行
            while (!Thread.interrupted()) {
                // 任务执行体
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("task: " + Thread.currentThread().getName());
            }
            System.out.println("121211111111");
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        // 第二次cancel false
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());


//        executorService.shutdown();
    }

    /**
     * todo 温和的终止方案2
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testCanacel2_2() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final AtomicBoolean running = new AtomicBoolean(true);
        final Future<Integer> future = executorService.submit(() -> {
            // todo 通过Thread.interrupted()方式，中断任务执行
            while (running.get()) {
                // 任务执行体
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("task: " + Thread.currentThread().getName());
            }
            System.out.println("121211111111");
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(future.cancel(true));
        // 第二次cancel false
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());


//        executorService.shutdown();
    }

    /**
     * todo 将整个线程池终止
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testCanacel2_1() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                final Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
        final AtomicBoolean running = new AtomicBoolean(true);
        final Future<Integer> future = executorService.submit(() -> {
            // todo 通过Thread.interrupted()方式，中断任务执行
            while (!Thread.interrupted()) {
                // 任务执行体
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("task: " + Thread.currentThread().getName());
            }
            System.out.println("121211111111");
            return 10;
        });
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(future.cancel(true));
        // 第二次cancel false
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());


//        executorService.shutdown();
    }

    /**
     * isDone: todo 完成可能是由于正常终止、异常、取消——在所有这些情况下，此方法都将返回true
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testIsDone() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        final Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                throw new RuntimeException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        //===============================

        final Integer result;
        try {
            result = future.get();
            System.out.println("normal:" + result + " is done " + future.isDone());
        } catch (Exception e) {
            System.out.println("exception: is done " + future.isDone());
        }

        //===============================

        executorService.shutdown();
    }
}
