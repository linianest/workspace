package com.ln.concurrent.chapter10;

import java.util.Arrays;
import java.util.Random;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter10
 * @Name:ThreadLocalComplexTest
 * @Author:linianest
 * @CreateTime:2020/3/28 21:40
 * @version:1.0
 * @Description TODO: 线程ThreadLocal
 */
public class ThreadLocalComplexTest {
    private final static ThreadLocal<String> threadlocal = new ThreadLocal<>();
    public static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadlocal.set("Thread-T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadlocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            threadlocal.set("Thread-T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadlocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("===========================");
        System.out.println(Thread.currentThread().getName() + " " + threadlocal.get());

    }
}
