package com.ln.concurrency.chapter4;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter4
 * @version: 1.0
 */

import java.util.Optional;

/**
 * @ClassName:ThreadSimpleAPI2
 * @Author:linianest
 * @CreateTime:2020/3/14 17:27
 * @version:1.0
 * @Description TODO:
 */
public class ThreadSimpleAPI2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-Index" + i).ifPresent(System.out::println);
            }
        });
        // 设置线程优先级最高
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
    }
}
