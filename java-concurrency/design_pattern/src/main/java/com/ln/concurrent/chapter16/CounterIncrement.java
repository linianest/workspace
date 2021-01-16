package com.ln.concurrent.chapter16;

import java.util.Random;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent
 * @Name:CounterIncrement
 * @Author:linianest
 * @CreateTime:2021/1/4 11:39
 * @version:1.0
 * @Description TODO: 线程统计数据
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;
    private static final Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {

        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean() {
        System.out.println("do some clean work for the second phase.current counter=" + counter);
    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }
}
