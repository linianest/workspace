package com.ln.concurrent.chapter17;

import java.util.Arrays;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter17
 * @Name:Channel
 * @Author:linianest
 * @CreateTime:2021/1/4 14:05
 * @version:1.0
 * @Description TODO: 定义流水线(环形队列)
 */
public class Channel {
    // 队列大小
    private final static int MAX_REQUEST = 100;
    // 请求队列
    private final Request[] requestQueue;
    // 队列头
    private int head;
    // 队列尾
    private int tail;
    // 计数器
    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int workers) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[workers];
        this.init();
    }

    private void init() {
        for (int i = 0; i < workerPool.length; i++) {
            workerPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    /**
     * push switch to start all of worker to worker
     */
    public void startWorker() {
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void put(Request request) {
        while (count >= requestQueue.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.requestQueue[tail] = request;
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        final Request request = this.requestQueue[head];
        this.head = (this.head + 1) % this.requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }

}
