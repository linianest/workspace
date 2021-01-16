package com.ln.concurrent.chapter13;

import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter13
 * @Name:ProducerAndConsumerThread
 * @Author:linianest
 * @CreateTime:2021/1/3 18:06
 * @version:1.0
 * @Description TODO: 客户端
 */
public class ProducerAndConsumerThread {
    public static void main(String[] args) {
        final MessageQueue messageQueue=new MessageQueue();
        IntStream.range(1,3).forEach(i->{
            new ProdcerThread(messageQueue,i).start();
        });
        IntStream.range(1,2).forEach(i->{
            new ConsumerThread(messageQueue,i).start();
        });
    }
}
