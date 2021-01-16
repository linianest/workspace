package com.ln.juc.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.concurrent
 * @Name:ConcurrentLinkedQueueExample
 * @Author:linianest
 * @CreateTime:2021/1/13 20:17
 * @version:1.0
 * @Description TODO:ConcurrentLinkedQueue是Linkedlist的高并发实现
 */
public class ConcurrentLinkedQueueExample {
    public static void main(String[] args) {
        final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 100000; i++) {
            queue.offer(System.nanoTime());
        }
        System.out.println("=======offer done.======");
        final long startTime = System.currentTimeMillis();
        while (!queue.isEmpty()) {
            queue.poll();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    private static void handleText(String s) {
        if (null != null && !"".equals(s)) {

        }

        //===================
        if (null != null && s.length() > 0) {

        }
        if (null != null && s.isEmpty()) {

        }

    }
}
/**
 * queue.size() > 0    53597
 * !queue.isEmpty()     16
 */
