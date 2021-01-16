package com.ln.juc.executors;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ExecutorServiceExample4
 * @Author:linianest
 * @CreateTime:2021/1/9 15:24
 * @version:1.0
 * @Description TODO:ExecutorService API使用详解
 */
public class ExecutorServiceExample4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testInvokeAny();
//        testInvokeAnyTimeOut();
//        testInvokeAll();
//        testInvokeAllTimeOut();
//        testSubmitRunnable();
        testSubmitRunnableWithResult();
    }

    /**
     * 返回任意一个,其他的线程取消了
     *
     * @throws InterruptedException
     * @throws ExecutionException   {@link ExecutorService#invokeAny(Collection)}
     */
    private static void testInvokeAny() throws InterruptedException, ExecutionException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed()
                .map(i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    return i;
                }).collect(Collectors.toList());
        final Integer value = executorService.invokeAny(callableList);
        System.out.println("===========finished=============");
        System.out.println(value);
    }

    /**
     * 带超时功能InvokeAny
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void testInvokeAnyTimeOut() throws InterruptedException, ExecutionException, TimeoutException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed()
                .map(i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    return i;
                }).collect(Collectors.toList());
        final Integer value = executorService.invokeAny(callableList, 3, TimeUnit.SECONDS);
        System.out.println("===========finished=============");
        System.out.println(value);
    }

    /**
     * 返回所有的线程结果，同步执行
     *
     * @throws InterruptedException
     */
    private static void testInvokeAll() throws InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed()
                .map(i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    return i;
                }).collect(Collectors.toList());
        final List<Future<Integer>> futures = executorService.invokeAll(callableList);
        System.out.println("===========finished=============");
        futures.forEach(System.out::println);
    }

    private static void testInvokeAllTimeOut() throws InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed()
                .map(i -> (Callable<Integer>) () -> {
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    return i;
                }).collect(Collectors.toList());
        final List<Future<Integer>> futures = executorService.invokeAll(callableList, 3, TimeUnit.SECONDS);
        System.out.println("===========finished=============");
        futures.forEach(System.out::println);
    }

    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        final Object result = future.get();
        System.out.println("result=" + result);
    }

    /**
     * future有结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testSubmitRunnableWithResult() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        String result = null;
        final Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);
        final Object r = future.get();
        System.out.println("result=" + r);
    }
}
