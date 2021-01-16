package com.ln.juc.executors;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:ExecutorsExample2
 * @Author:linianest
 * @CreateTime:2021/1/7 15:54
 * @version:1.0
 * @Description TODO:newWorkStealingPool ExecutorService使用详解
 */
public class ExecutorsExample2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newWorkStealingPool();
        // 获取当前电脑的cpu核数
//        Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);

        final List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i ->
                (Callable<String>) () -> {
                    System.out.println("Thread: " + Thread.currentThread().getName());
                    sleepSeconds(2);
                    return "Task-" + i;
                }
        ).collect(Collectors.toList());
        // 等待任务执行结果后，返回future，通过future获取执行的结果
        executorService.invokeAll(callableList).stream().map(
                future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        ).forEach(System.out::println);

    }

    private static void sleepSeconds(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
