package com.ln.concurrent.chapter9;

import java.util.LinkedList;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter9
 * @Name:RequestQueue
 * @Author:linianest
 * @CreateTime:2020/3/28 19:31
 * @version:1.0
 * @Description TODO: 请求队列
 */
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }

    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
