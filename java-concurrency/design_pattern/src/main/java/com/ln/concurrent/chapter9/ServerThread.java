package com.ln.concurrent.chapter9;

import java.util.Random;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter9
 * @Name:ServerThread
 * @Author:linianest
 * @CreateTime:2020/3/28 20:03
 * @version:1.0
 * @Description TODO: 服务端处理线程
 */
public class ServerThread extends Thread {

    private final RequestQueue queue;

    private final Random random;

    private volatile boolean closed = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }


    @Override
    public void run() {
        while (!closed) {
            Request request = queue.getRequest();
            if (null == request) {
                System.out.println("Received the empty request.");
                continue;
            }
            System.out.println("Server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void close() {
        this.closed = true;
        this.interrupt();
    }
}
