package com.ln.concurrency.chapter9;
/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrency.chapter9
 * @version: 1.0
 */

import java.util.stream.Stream;

/**
 * @ClassName:ProduceConsumerVersion3
 * @Author:linianest
 * @CreateTime:2020/3/16 13:46
 * @version:1.0
 * @Description TODO: 多线程下，生产者消费者模型以及notifyAll()方法
 */
public class ProduceConsumerVersion3 {
    private int i = 0;

    final private Object LOCK = new Object();

    // 生产者是否已经生产
    private volatile boolean isProduced = false;

    /**
     * 实现：如果生产者已经生产,生产者进入等待状态,
     * 否则,进行生产,生产完后,通知消费者,已经生产了数据,标志位并设置成true
     */
    public void produce() {
        synchronized (LOCK) {
            // 如果生产者已经生产
            /**
             * 注意:这里必须使用while,如果使用if会出现两个生产者重复生产。
             * 原因：一个线程抢到了,进入后面的流程,一个线程没抢到,等消费者消费后,又生产了上一个生产者生产的数据
             * 解决方案：使用while与if的区别,while是循环判断,if是单支判断
             */
            while (isProduced) {
                try {
                    // 生产者线程处于等待状态
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 如果没有生产，进行生产
            i++;
            System.out.println(Thread.currentThread().getName() + "->" + i);
            // 生产完,通知所有的消费者消费
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    /**
     * 消费者实现：如果生产者没有生产数据,消费者线程进入等待状态,
     * 否则,消费者进行消费数据,消费完成后,并通知生产者生产,将标志位设置成false
     */
    public void consumer() {
        synchronized (LOCK) {
            // 如果生产者已经生产
            while (!isProduced) {
                try {
                    // 消费者进行线程等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 开始消费
            System.out.println(Thread.currentThread().getName() + "->" + i);
            // 消费完后,激活生产者线程进行生产,并将标志位设置成false
            LOCK.notifyAll();
            isProduced = false;
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();
        Stream.of("P1", "P2", "P3").forEach(p -> {
            new Thread(p) {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                        try {
                            Thread.sleep(1_0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
        });

        Stream.of("C1", "C2", "C3").forEach(c -> {
            new Thread(c) {
                @Override
                public void run() {
                    while (true) {
                        pc.consumer();
                        try {
                            Thread.sleep(1_0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

    }
}
