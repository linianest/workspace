package com.ln.juc.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:CompletionServiceExample2
 * @Author:linianest
 * @CreateTime:2021/1/10 12:10
 * @version:1.0
 * @Description TODO:CompletionService
 */
public class CompletionServiceExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
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
        final ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
//        final ArrayList<Future<Integer>> futures = new ArrayList<>();
        callableList.stream().forEach(callable -> {
            completionService.submit(callable);
        });
       /*  Future<Integer> future = null;
        while ((future = completionService.take()) != null) {
            System.out.println(future.get());
        }*/
        Future<Integer> poll = completionService.poll();
        System.out.println(poll.get());

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
