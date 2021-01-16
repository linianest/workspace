package com.ln.concurrent.chapter14;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter14
 * @Name:JDKCountDown
 * @Author:linianest
 * @CreateTime:2021/1/4 9:12
 * @version:1.0
 * @Description TODO: jdk自带的阻塞主线程模式，类似于join的功能
 */

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 通过统计执行线程的个数，来判断执行线程是否执行完成，主线程阻塞
 */
public class JDKCountDown {
    private static final Random random = new Random(System.currentTimeMillis());


    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        System.out.println("多线程执行任务");

        IntStream.rangeClosed(1, 5).forEach(i ->
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is working.");
                try {
                    Thread.sleep(random.nextInt(1_000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 统计当前线程执行完成的个数
                latch.countDown();
            }, String.valueOf(i)).start()
        );
        // 主线程阻塞
        latch.await();
        System.out.println("多线程执行任务全部结束,准备第二阶段任务");
        System.out.println("......................");
        System.out.println("FINISH.");
    }
}
