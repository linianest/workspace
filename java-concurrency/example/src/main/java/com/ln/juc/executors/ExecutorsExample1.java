package com.ln.juc.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ExecutorsExample1
 * @Author:linianest
 * @CreateTime:2021/1/7 15:18
 * @version:1.0
 * @Description TODO: newCache、newFixed、newSingle的Executors使用详解
 */
public class ExecutorsExample1 {
    public static void main(String[] args) throws InterruptedException {


//        useCachedThreadPool();
//        useFixedSizeThreadPool();
        useSingleThreadPool();
    }

    /**
     * thread与SingleThreadExecutor的区别：
     * 1：thread执行完成后，就死亡，而SingleThreadExecutor不会，有一个任务队列
     *   return new FinalizableDelegatedExecutorService
     *        (new ThreadPoolExecutor(1, 1,
     *        0L, TimeUnit.MILLISECONDS,
     *        new LinkedBlockingQueue<Runnable>()));
     *   等价于newFixedThreadPool(1);
     */
    private static void useSingleThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        executorService.execute(() -> System.out.println("-------------"));
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 50).boxed()
                .forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                            System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    /**
     * 使用固定大小的线程池:LinkedBlockingQueue队列的最大值为Interget.Max
     * 特点：核心线程数与最大线程数相等
     * new ThreadPoolExecutor(nThreads, nThreads,
     * 0L, TimeUnit.MILLISECONDS,
     * new LinkedBlockingQueue<Runnable>());
     */
    private static void useFixedSizeThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        executorService.execute(() -> System.out.println("-------------"));
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 50).boxed()
                .forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                            System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }

    /**
     * 使用缓存线程池
     * <p>
     * These pools will typically improve the performance
     * of programs that execute many short-lived asynchronous tasks.
     * </p>
     * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     * 60L, TimeUnit.SECONDS,
     * new SynchronousQueue<Runnable>());
     */
    private static void useCachedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
        executorService.execute(() -> System.out.println("-------------"));
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

        IntStream.range(0, 50).boxed()
                .forEach(i -> {
                    executorService.execute(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(10);
                            System.out.println(Thread.currentThread().getName() + " [" + i + "] finish done.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                });
        TimeUnit.SECONDS.sleep(1);
        System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());
    }
}
