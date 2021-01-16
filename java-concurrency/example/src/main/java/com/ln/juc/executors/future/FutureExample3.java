package com.ln.juc.executors.future;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.executors
 * @Name:FutureExample3
 * @Author:linianest
 * @CreateTime:2021/1/10 11:36
 * @version:1.0
 * @Description TODO:future&callable详解
 */
public class FutureExample3 {
    public static void main(String[] args) {

    }

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


    }
}
