package com.ln.concurrent.chapter17;

import java.util.Random;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter17
 * @Name:TransportThread
 * @Author:linianest
 * @CreateTime:2021/1/4 14:27
 * @version:1.0
 * @Description TODO: 装配工人
 */
public class TransportThread extends Thread{
    private final Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true ; i++) {
                Request request = new Request(getName(),i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
