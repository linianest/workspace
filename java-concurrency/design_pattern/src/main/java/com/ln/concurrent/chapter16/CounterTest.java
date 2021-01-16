package com.ln.concurrent.chapter16;

import com.ln.concurrent.chapter16.CounterIncrement;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter15
 * @Name:CounterTest
 * @Author:linianest
 * @CreateTime:2021/1/4 11:46
 * @version:1.0
 * @Description TODO: 两阶段终结线程设计模式
 */
public class CounterTest {
    public static void main(String[] args) throws InterruptedException {
        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();
        Thread.sleep(10_000L);
        counterIncrement.close();
    }
}
