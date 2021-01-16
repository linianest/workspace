package com.ln.concurrent.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter13
 * @Name:ComsumerThread
 * @Author:linianest
 * @CreateTime:2021/1/3 18:03
 * @version:1.0
 * @Description TODO: 消费者
 */
public class ConsumerThread extends Thread {

    private final MessageQueue messageQueue;

    public static final Random random = new Random(System.currentTimeMillis());

    // 具有原子性的计数器
    private final static AtomicInteger counter = new AtomicInteger(0);

    public ConsumerThread(MessageQueue messageQueue, int seq
    ) {
        // 设置线程的名称
        super("Consumer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " take message " + message.getData());
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
