package com.ln.concurrent.chapter15;

import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter15
 * @Name:PerThreadClient
 * @Author:linianest
 * @CreateTime:2021/1/4 11:17
 * @version:1.0
 * @Description TODO: 单线程处理请求
 */
public class PerThreadClient {
    public static void main(String[] args) {
        final MessageHander hander=new MessageHander();
        IntStream.rangeClosed(0,10)
                .forEach(i->{
                    hander.request(new Message(String.valueOf(i)));
                });
        hander.shutdown();
    }
}
