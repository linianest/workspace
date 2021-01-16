package com.ln.concurrent.chapter13;

import java.util.LinkedList;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter13
 * @Name:MessageQueue
 * @Author:linianest
 * @CreateTime:2021/1/3 17:38
 * @version:1.0
 * @Description TODO: 消息队列:有大小限制
 */
public class MessageQueue {

    private final LinkedList<Message> queue;
    // 设置消息队列大小
    private final static int DEFAULT_MAX_LIMIT = 100;
    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(final int limit) {
        this.queue = new LinkedList<>();
        this.limit = limit;
    }

    public void put(final Message message) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() > limit) {
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit() {
        return this.limit;
    }

    public int getMessageSize() {
        synchronized (queue) {
            return queue.size();
        }
    }
}
