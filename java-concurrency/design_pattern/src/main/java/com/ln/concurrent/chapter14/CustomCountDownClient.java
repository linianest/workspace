package com.ln.concurrent.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter14
 * @Name:CustomCountDownClient
 * @Author:linianest
 * @CreateTime:2021/1/4 11:04
 * @version:1.0
 * @Description TODO: 测试
 */
public class CustomCountDownClient {
    private static final Random random = new Random(System.currentTimeMillis());


    public static void main(String[] args) throws InterruptedException {
        final CountDown latch = new CountDown(5);
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
                    latch.down();
                }, String.valueOf(i)).start()
        );
        // 主线程阻塞
        latch.await();
        System.out.println("多线程执行任务全部结束,准备第二阶段任务");
        System.out.println("......................");
        System.out.println("FINISH.");
    }
}
