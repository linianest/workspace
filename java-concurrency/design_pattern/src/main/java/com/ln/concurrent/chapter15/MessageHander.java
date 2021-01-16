package com.ln.concurrent.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter15
 * @Name:MessageHander
 * @Author:linianest
 * @CreateTime:2021/1/4 11:14
 * @version:1.0
 * @Description TODO: 请求数据封装在线程中,常用在后台服务端
 */
public class MessageHander {
    private static final Random random = new Random(System.currentTimeMillis());
    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1_000));
                System.out.println("The message-" + value + " will be handle by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 任务执行完成，线程池销毁
     */
    public void shutdown() {
        ((ExecutorService) executor).shutdown();
    }
}
