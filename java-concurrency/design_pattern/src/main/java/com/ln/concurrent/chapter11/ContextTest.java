package com.ln.concurrent.chapter11;

import java.util.stream.IntStream;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter11
 * @Name:ContextTest
 * @Author:linianest
 * @CreateTime:2020/3/29 19:45
 * @version:1.0
 * @Description TODO: 测试上下文请求任务
 */
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1, 5)
                .forEach(i -> {
                    new Thread(new ExecutionTask()).start();
                });
    }
}
