package com.ln.juc.blocking;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.juc.blocking
 * @Name:ConcurrentLinkedQueueExample
 * @Author:linianest
 * @CreateTime:2021/1/12 17:44
 * @version:1.0
 * @Description TODO:ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，它采用先进先出的规则对节点进行排序，当我们添加一个元素的时候，它会添加到队列的尾部，当我们获取一个元素时，它会返回队列头部的元素。它采用了“wait－free”算法来实现，该算法在Michael & Scott算法上进行了一些修改
 */
public class ConcurrentLinkedQueueExample {




    public static void main(String[] args) {
        final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 100000; i++) {

            queue.offer(System.nanoTime());
        }
        System.out.println("=====offer done=========");
        final long startTime = System.currentTimeMillis();
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }
}
/**
 * 66560
 * 5171
 */
