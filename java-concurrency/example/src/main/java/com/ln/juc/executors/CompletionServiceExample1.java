package com.ln.juc.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:CompletionServiceExample1
 * @Author:linianest
 * @CreateTime:2021/1/10 11:39
 * @version:1.0
 * @Description TODO:CompletionService
 */
public class CompletionServiceExample1 {
    /**
     * todo Demo the future defect:future的缺陷（1.8以前）
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        futureDefect1();
        futureDefect2();
    }

    /**
     * todo future缺陷:
     * 1、no callback
     */
    private static void futureDefect1() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        future.get();
        System.out.println("===========================");
    }

    /**
     * todo future缺陷2:无法按执行完成的先后顺序获取任务结果(只能改进代码)
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    // todo 执行一组任务
    private static void futureDefect2() throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        final List<Callable<Integer>> callableList = Arrays.asList(
                () -> {
                    sleep(3);
                    System.out.println("the 3 finished");
                    return 3;
                },
                () -> {
                    sleep(5);
                    System.out.println("the 5 finished");
                    return 5;
                }
        );
/*        final List<Future<Integer>> futures = executorService.invokeAll(callableList);
        final Integer v1 = futures.get(1).get();
        System.out.println(v1);
        final Integer v2 = futures.get(0).get();
        System.out.println(v2);*/
        System.out.println("===========================");
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < callableList.size(); i++) {
            futures.add(executorService.submit(callableList.get(i)));
        }
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
    }

    /**
     * todo sleep the specify MILLISECONDS
     *
     * @param MILLISECONDS
     */
    private static void sleepMilliSeconds(long MILLISECONDS) {
        try {
            TimeUnit.MILLISECONDS.sleep(MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * todo sleep the specify SECONDS
     *
     * @param SECONDS
     */
    private static void sleep(long SECONDS) {
        try {
            TimeUnit.SECONDS.sleep(SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
