package com.ln.concurrency.chapter3;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter3
 * @version: 1.0
 */

import java.util.Arrays;

/**
 * @ClassName:CreateThread2
 * @Author:linianest
 * @CreateTime:2020/3/14 9:54
 * @version:1.0
 * @Description TODO:
 */
public class CreateThread2 {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        System.out.println(Thread.currentThread().getName());

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        System.out.println(threadGroup.activeCount());
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);

    }
}
