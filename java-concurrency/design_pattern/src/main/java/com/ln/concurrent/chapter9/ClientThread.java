package com.ln.concurrent.chapter9;

import java.util.Random;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter9
 * @Name:ClientThread
 * @Author:linianest
 * @CreateTime:2020/3/28 19:55
 * @version:1.0
 * @Description TODO: 客户端线程
 */
public class ClientThread extends Thread{
    private final RequestQueue queue;

    private final Random random;
    private final String sendValue;

    public ClientThread(RequestQueue queue, String sendValue){
        this.queue = queue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
           System.out.println("Client -> Request "+sendValue);
           queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
