package com.ln.concurrent.chapter17;

import java.util.Random;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter17
 * @Name:WorkerThread
 * @Author:linianest
 * @CreateTime:2021/1/4 14:08
 * @version:1.0
 * @Description TODO: 工作线程，从流水线取出工人装配的产品
 */
public class WorkerThread extends Thread {
    private final Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            channel.take().exeute();
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
