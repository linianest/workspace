package com.ln.juc.executors;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ExecutorServiceExample2
 * @Author:linianest
 * @CreateTime:2021/1/9 10:46
 * @version:1.0
 * @Description TODO:ExecutorService 四大内置拒绝策略
 */
public class ExecutorServiceExample2 {
    public static void main(String[] args) throws InterruptedException {
//        testAbortPolicy();
//        testDiscardPolicy();
//        testDiscardPolicy();
//        testDiscardOldestPolicy();
        testCallerRunsPolicy();
    }

    /**
     * todo 线程池中的任务满了后，新的任务在队列中等待，现在新的任务又来了，将队列中任务拒绝掉
     */
    private static void testDiscardOldestPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.DiscardOldestPolicy());

        IntStream.range(0, 3).boxed()
                .forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
    }
    /**
     * todo 直接拒绝，将任务丢弃，什么都不操作返回，不知道发生了什么
     */
    private static void testDiscardPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.DiscardPolicy());

        IntStream.range(0, 3).boxed()
                .forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> System.out.println("x"));
    }

    /**
     * todo 当线程池满了后，谁提交，谁执行，目前是main线程提交，所以是main线程执行提交的任务
     */
    private static void testCallerRunsPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.CallerRunsPolicy());

        IntStream.range(0, 3).boxed()
                .forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> {
            System.out.println("x");
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("=============================");
    }


    /**
     * todo 通过抛异常的方式拒绝
     */
    private static void testAbortPolicy() throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
                r -> {
                    Thread t = new Thread(r);
                    return t;
                }, new ThreadPoolExecutor.AbortPolicy());

        IntStream.range(0, 3).boxed()
                .forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });
        TimeUnit.SECONDS.sleep(1);
        executorService.execute(() -> System.out.println("x"));
    }
}
