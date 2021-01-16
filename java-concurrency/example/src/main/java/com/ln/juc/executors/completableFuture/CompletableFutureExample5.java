package com.ln.juc.executors.completableFuture;

import com.ln.juc.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors.completableFuture
 * @Name:CompletableFutureExample5
 * @Author:linianest
 * @CreateTime:2021/1/11 13:11
 * @version:1.0
 * @Description TODO:CompletableFuture
 */
public class CompletableFutureExample5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        getNow();
        complete();
        Thread.currentThread().join();
    }

    private static void getNow() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.sleep(5);
            return "HELLO";
        });
        String result = future.getNow("world.");
        System.out.println(result);
        System.out.println(future.get());
    }

    /**
     * complete:判断当前future是否完成
     * todo 使用场景：假如get获取数据失败，可以用complete的数据
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void complete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.sleep(5);
            System.out.println(" i will be still process.");
            return "HELLO";
        });
         boolean finished = future.complete("WORLD.");
        System.out.println(finished);
        System.out.println(future.get());
    }
}
