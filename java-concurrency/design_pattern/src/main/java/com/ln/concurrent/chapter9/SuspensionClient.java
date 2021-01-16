package com.ln.concurrent.chapter9;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter9
 * @Name:SuspensionClient
 * @Author:linianest
 * @CreateTime:2020/3/28 20:20
 * @version:1.0
 * @Description TODO: 高并发请求,缓存队列
 */
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
//        serverThread.join();

        Thread.sleep(10000);

        serverThread.close();
    }
}
