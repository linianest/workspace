package com.ln.juc.executors.completableFuture;

import com.ln.juc.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors.completableFuture
 * @Name:CompletableFutureExample4
 * @Author:linianest
 * @CreateTime:2021/1/11 12:54
 * @version:1.0
 * @Description TODO:CompletableFuture
 */
public class CompletableFutureExample4 {
    public static void main(String[] args) throws InterruptedException {
        thenAcceptBoth();
        Thread.currentThread().join();
    }

    private static void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("start the supplyAsync");
            ThreadUtils.sleep(5);
            System.out.println("end the supplyAsync");
            return "thenAcceptBoth";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("start the thenAcceptBoth");
            ThreadUtils.sleep(5);
            System.out.println("end the thenAcceptBoth");
            return 100;
        }), (s, i) -> System.out.println(s+"--------"+i));
    }
}
