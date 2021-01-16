package com.ln.concurrent.chapter8;

/**
 * @ProjectName: java-concurrency
 * @Package: com.ln.concurrent.chapter8
 * @Name:FutureTask
 * @Author:linianest
 * @CreateTime:2020/3/25 14:56
 * @version:1.0
 * @Description TODO:定义接口，返回任意类型的结果
 */
public interface Future<T> {
    // 调用者调用的方法
    T get() throws InterruptedException;
}
