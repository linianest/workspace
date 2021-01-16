package com.ln.juc.executors.completableFuture;

import com.ln.juc.utils.ThreadUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors.completableFuture
 * @Name:CompletableFutureExample3
 * @Author:linianest
 * @CreateTime:2021/1/11 10:39
 * @version:1.0
 * @Description TODO:CompletableFuture API详解
 */
public class CompletableFutureExample3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
    }
    private static void test1() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello");
//                .whenComplete((v,r)-> System.out.println("done"));
        future.whenComplete((v,t)->{
            System.out.println("====result=====");
            ThreadUtils.sleep(2);
            System.out.println("====over====");
        });
/*
        future.whenCompleteAsync((v,t)->{
            System.out.println("====result=====");
            ThreadUtils.sleep(2);
            System.out.println("====over====");
        });
*/
        System.out.println(future.get());

        Thread.currentThread().join();
    }
}
