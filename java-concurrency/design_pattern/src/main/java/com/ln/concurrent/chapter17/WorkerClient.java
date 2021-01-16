package com.ln.concurrent.chapter17;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter17
 * @Name:WorkerClient
 * @Author:linianest
 * @CreateTime:2021/1/4 14:36
 * @version:1.0
 * @Description TODO: 客户端
 */
public class WorkerClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Alex",channel).start();
        new TransportThread("Jack",channel).start();
        new TransportThread("William",channel).start();
    }
}
